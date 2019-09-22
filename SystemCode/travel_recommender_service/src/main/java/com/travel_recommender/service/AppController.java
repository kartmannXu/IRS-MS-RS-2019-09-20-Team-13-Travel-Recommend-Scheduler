package com.travel_recommender.service;

import com.travel_recommender.DAO.SpotRepository;
import com.travel_recommender.model.Spot;
import com.travel_recommender.model.UserAnswers;
import com.travel_recommender.opta.Day;
import com.travel_recommender.opta.Solution;
import com.travel_recommender.opta.SpotSnippet;
import com.travel_recommender.opta.TimeCapsule;
import com.travel_recommender.service.scheduleSolver.SolverManager;
import com.travel_recommender.service.scheduleSolver.SolverStatus;
import com.travel_recommender.service.scheduleSolver.SpotArrangeService;
import com.travel_recommender.service.viewModel.SpotSnippetViewModel;
import com.travel_recommender.service.viewModel.UserAnswerViewModel;
import org.apache.commons.lang3.StringUtils;
import org.jbpm.services.api.DeploymentService;
import org.jbpm.services.api.ProcessService;
import org.jbpm.services.api.RuntimeDataService;
import org.kie.server.services.api.KieServer;
import org.kie.server.springboot.jbpm.ContainerAliasResolver;
import org.optaplanner.core.api.score.Score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
public class AppController {

    @Autowired
    private DeploymentService deploymentService;

    @Autowired
    private RuntimeDataService runtimeDataService;

    @Autowired  // eliminate setter getter
    private ProcessService processService;

    @Autowired
    private KieServer kieServer;

    @Autowired
    private ContainerAliasResolver containerAliasResolver;

    @Autowired
    SpotRepository repo;

    // private UserAnswers userAnswers;
    private UserAnswers userAnswersHolder;
    // config for recommender KieModule
    private long processInstanceId;
    private final String containerAlias = "travel-recommender";
    private final String processId = "travel-recommender.travelrecommender";

    private final double scoreThreshold = 3;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
    private final float gourmetThreshold = 0.1f;
    private SimpleDateFormat timefmt = new SimpleDateFormat("yyyy-mm-dd HH:mm");
    private HashMap<String, String> criticalTimes = initCriticalTimes();
    private static boolean scoreBase = true;
    // TODO: manage resultlist indexing in recommenderSolverManager
    private Map<Comparable<?>, List<Spot>> clientIdToResultList = new HashMap<>();
    private Map<Comparable<?>, Solution> clientIdToSolution = new HashMap<>();
    private Map<Comparable<?>, List<Day>> clientIdToDayList = new HashMap<>();

    // Optaplanner Module
    @Autowired
    private SolverManager<Solution> solverManager;

    @PostMapping("/page2") // process page1 request
    public UserAnswerViewModel page2(@RequestBody UserAnswerViewModel usrAnsVM, // bound with answer model from page1
                                     BindingResult bindingResult) throws ValidationException, ParseException {
        if (bindingResult.hasErrors()) { throw new ValidationException("Error in storing your answer"); };

        userAnswersHolder = new UserAnswers();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));

        System.out.println(StringUtils.repeat("-", 20) + "\nInitializing: " + userAnswersHolder);

        userAnswersHolder.setQnsCountryId(usrAnsVM.getQnsCountryId());
        userAnswersHolder.setQnsDepartureTime((int) simpleDateFormat.parse(usrAnsVM.getQnsDepartureTime())
                .getTime() / (1000 * 60));     //minute unit
        userAnswersHolder.setQnsLeavingTime((int) simpleDateFormat.parse(usrAnsVM.getQnsLeavingTime())
                .getTime() / (1000 * 60));
        userAnswersHolder.setQnsKidElder(usrAnsVM.isQnsKidElder());
        userAnswersHolder.setQnsCultural(usrAnsVM.isQnsCultural());
        userAnswersHolder.setQnsBudget(usrAnsVM.getQnsBudget());
        userAnswersHolder.setQnsFoodExpectation(usrAnsVM.isQnsFoodExpectation());
        userAnswersHolder.setQnsCrowded(usrAnsVM.isQnsCrowded());
        userAnswersHolder.setDowntown(usrAnsVM.isQnsDowntown());
        userAnswersHolder.setQnsSouveniers(usrAnsVM.isQnsSouveniers());
        userAnswersHolder.setQnsView(usrAnsVM.isQnsView());

            userAnswersHolder.setGardens(true);
            userAnswersHolder.setParks(true);
            userAnswersHolder.setMuseums(true);
            userAnswersHolder.setObservation_deck(true);
            userAnswersHolder.setZoo(true);
            userAnswersHolder.setThemeparks(true);
            userAnswersHolder.setNeighbourhoods(true);
            userAnswersHolder.setReligious_Sites(true);
            userAnswersHolder.setLandmarks(true);
            userAnswersHolder.setHistorical_Sites(true);
            userAnswersHolder.setIsland(true);
            userAnswersHolder.setShopping_Malls(true);
            userAnswersHolder.setBridges(true);
            userAnswersHolder.setBeaches(true);

        System.out.println(StringUtils.repeat("-", 20) + "\nParameters: " + userAnswersHolder);

        runRecommendProcess(userAnswersHolder);

        System.out.println(StringUtils.repeat("-", 20) + "\nInstance ID: " + processInstanceId);
        Map<String, Object> processVariables  = processService.getProcessInstanceVariables(processInstanceId);

        userAnswersHolder = (UserAnswers) processVariables.get("userAnsHolder");
        System.out.println(StringUtils.repeat("-", 20) + "\nReturned from KIE: " + userAnswersHolder);
        usrAnsVM.setTypes(getTypes(userAnswersHolder));
        usrAnsVM.setId(processInstanceId);
        return usrAnsVM;
    }

    @PostMapping("{clientId}/recommendation")
    public void page3(@PathVariable Comparable<?> clientId,
                            @RequestBody UserAnswerViewModel usrAnsVM,
                            BindingResult bindingResult) throws ValidationException, ParseException {

        if (bindingResult.hasErrors()) { throw new ValidationException("Error in storing your answer"); };

        String types = usrAnsVM.getTypes().toLowerCase();
        if (!types.contains("gardens")) { userAnswersHolder.setGardens(false); }
        if (!types.contains("parks")) { userAnswersHolder.setParks(false); }
        if (!types.contains("museums")) { userAnswersHolder.setMuseums(false); }
        if (!types.contains("observation_deck")) { userAnswersHolder.setObservation_deck(false); }
        if (!types.contains("zoo")) { userAnswersHolder.setZoo(false); }
        if (!types.contains("themeparks")) { userAnswersHolder.setThemeparks(false); }
        if (!types.contains("neighbourhoods")) { userAnswersHolder.setNeighbourhoods(false); }
        if (!types.contains("religious_sites")) { userAnswersHolder.setReligious_Sites(false); }
        if (!types.contains("landmarks")) { userAnswersHolder.setLandmarks(false); }
        if (!types.contains("historical_sites")) { userAnswersHolder.setHistorical_Sites(false); }
        if (!types.contains("island")) { userAnswersHolder.setIsland(false); }
        if (!types.contains("shopping_malls")) { userAnswersHolder.setShopping_Malls(false); }
        if (!types.contains("bridges")) { userAnswersHolder.setBridges(false); }
        if (!types.contains("beaches")) { userAnswersHolder.setBeaches(false); }

        System.out.println(StringUtils.repeat("-", 20) + "\nSearch Criteria: " + userAnswersHolder.toString());

        clientIdToResultList.put(clientId,
                repo.findSpot(
                    userAnswersHolder.getQnsCountryId(),
                    boolToFloat(userAnswersHolder.isGourmet()),
                    boolToFloat(userAnswersHolder.isQnsCultural()),
                    boolToFloat(userAnswersHolder.isBustle()),
                    boolToFloat(userAnswersHolder.isFamily()),
                    boolToFloat(userAnswersHolder.isShopping()),
                    boolToFloat(userAnswersHolder.isResort()),
                    boolToFloat(userAnswersHolder.isDowntown()),
                    scoreThreshold,
                    boolToInt(userAnswersHolder.isGardens()),
                    boolToInt(userAnswersHolder.isParks()),
                    boolToInt(userAnswersHolder.isMuseums()),
                    boolToInt(userAnswersHolder.isObservation_deck()),
                    boolToInt(userAnswersHolder.isZoo()),
                    boolToInt(userAnswersHolder.isThemeparks()),
                    boolToInt(userAnswersHolder.isNeighbourhoods()),
                    boolToInt(userAnswersHolder.isReligious_Sites()),
                    boolToInt(userAnswersHolder.isLandmarks()),
                    boolToInt(userAnswersHolder.isHistorical_Sites()),
                    boolToInt(userAnswersHolder.isIsland()),
                    boolToInt(userAnswersHolder.isShopping_Malls()),
                    boolToInt(userAnswersHolder.isBridges()),
                    boolToInt(userAnswersHolder.isBeaches()))
        );
        eliminateListBasedOnBudget(clientId);
        System.out.println(clientIdToResultList);
        solveService(clientId);
    }

    @GetMapping("/{clientId}/resultList")
    public List<Spot> page3recommend(@PathVariable Comparable<?> clientId) {
        eliminateListBasedOnSchedule(clientId);
        return clientIdToResultList.get(clientId);
    }

    @GetMapping("/{clientId}/recommendandschedule")
    public List<SpotSnippetViewModel> page3Schedule(@PathVariable Comparable<?> clientId) {

        List<SpotSnippetViewModel> spotVMs = new ArrayList<>();
        Solution solution = clientIdToSolution.get(clientId);

        List<Day> days = clientIdToDayList.get(clientId);
        if (solution != null) {
            // convert solution to front-end feasible View Model
            for (com.travel_recommender.opta.SpotSnippet snippet : solution.getSnippetList()) {
                SpotSnippetViewModel spotVM = new SpotSnippetViewModel();
                spotVM.setSpotId(snippet.getSpot().getSpotId());
                Spot daoSpot = repo.findSpotBySpot_id(spotVM.getSpotId()).get(0);
                spotVM.setSpotName(daoSpot.getSpot_name().replace("_", " "));

                // find day corresponding to the time capsule assigned to this spot snippet.
                // filter out infeasible solutions
                TimeCapsule capsule = snippet.getTimeCapsule();
                if (capsule == null) { continue; }
                List<Day> corresDays = days.stream().filter(day -> day.getTimeCapsules().contains(capsule)).collect(Collectors.toList());
                System.out.println("CorresDays: " + corresDays);

                // filter out infeasible solutions
                if (!days.isEmpty()) {
                    Day assignedDay = corresDays.get(0);
                    spotVM.setDay(assignedDay.getId().intValue());
                    spotVM.setTimeCapsuleId((int) (capsule.getId() % assignedDay.getRelativeTimeCapsuleIndex(assignedDay.getEnd_time())));
                    spotVM.setEst_duration(snippet.getSpot().getEst_duration());
                    spotVMs.add(spotVM);
                }
            }
            System.out.println("spotVMS: " + spotVMs);
        }
        return spotVMs;
    }

    @PostMapping("/{clientId}/solve")
    public void solve(@PathVariable Comparable<?> clientId) throws ParseException {
        solveService(clientId);
    }

    @GetMapping("/{clientId}/bestSolution")
    public Solution bestSolution(@PathVariable Comparable<?> clientId) {
        return solverManager.getBestSolution(clientId);
    }

    @GetMapping("/{clientId}/bestScore")
    public Score bestScore(@PathVariable Comparable<?> clientId) {
        return solverManager.getBestScore(clientId);
    }

    @GetMapping("/{clientId}/solverStatus")
    public SolverStatus solverStatus(@PathVariable Comparable<?> clientId) {
        return solverManager.getSolverStatus(clientId);
    }

    private void runRecommendProcess(UserAnswers userAnswersHolder) {
        Map<String, Object> processInputs = new HashMap<>();
        processInputs.put("userAnsHolder", userAnswersHolder);
        String resolveContainerId = containerAliasResolver.latest(containerAlias);
        System.out.println(resolveContainerId);
        processInstanceId = processService.startProcess(resolveContainerId, processId, processInputs);
    }

    private static float boolToFloat(Boolean b) {
        return b ? 1.0f : 0.0f;
    }

    private static int boolToInt(Boolean b) {
        return b ? 1 : 0;
    }

    private void eliminateListBasedOnBudget(Comparable<?> clientId) {
        List<Spot> resultList = clientIdToResultList.get(clientId);
        if (resultList.isEmpty()) {
            return;
        }
        if (scoreBase) {
            if (userAnswersHolder.getQnsBudget() < Long.MAX_VALUE) {
                System.out.println(StringUtils.repeat("-", 20) + "\nRemoving spots beyond budget");
                float sumPrice = 0.0f;
                for (int i = 0; i < resultList.size(); ++i) {
                    if (sumPrice >= userAnswersHolder.getQnsBudget()) {
                        System.out.println("Removing spot: " + resultList.get(i).getSpot_name() + ",\t" +
                                " list size reduced to " + resultList.size() + ".");
                        resultList.subList(i + 1, resultList.size()).clear();
                        break;
                    } else {
                        sumPrice += resultList.get(i).getTicket();
                    }
                }
            }
        }
        clientIdToResultList.put(clientId, resultList);
    }

    private void eliminateListBasedOnSchedule(Comparable<?> clientId) {
        List<Spot> resultList = clientIdToResultList.get(clientId);
        if (resultList.isEmpty()) {
            return;
        }
        Solution solution = solverManager.getBestSolution(clientId);
        
        List<SpotSnippet> snippetsArranged = solution.getSnippetList();
        snippetsArranged.removeIf(spotSnippet -> spotSnippet.getTimeCapsule() == null);

        List<com.travel_recommender.opta.Spot> spotsArranged = snippetsArranged.stream()
                .map(SpotSnippet::getSpot).collect(Collectors.toList());
        System.out.println(spotsArranged);

        List<Integer> resultIds = spotsArranged.stream().map(
                com.travel_recommender.opta.Spot::getSpotId).collect(Collectors.toList());
        resultList.removeIf(spot -> !resultIds.contains(spot.getSpot_id()));
        System.out.println("resultList size reduced to " + resultList.size());
        clientIdToResultList.put(clientId, resultList);
        clientIdToSolution.put(clientId, solution);
    }

    private List<Integer> encodeTimeInWeek(String times) throws ParseException {
        List<Integer> timeList = new ArrayList<>();
        for (String timeStr: times.split(",")) {
            timeList.add(DateTime2Int(userAnswersHolder.getQnsDepartureTime(), 0, timeStr));
        }
        return timeList;
    }

    private Integer DateTime2Int(int departTimeInt, long currentDate, String timeStr) throws ParseException {
        // Prevent negative output of parsers
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        timefmt.setTimeZone(TimeZone.getTimeZone("GMT"));
        Date date = new Date();
        date.setTime(departTimeInt + (60 * 24 * 60 * 1000) * currentDate);
        return (int) timefmt.parse(simpleDateFormat.format(date) + " " + timeStr).getTime() / (1000 * 60);       // in minute
    }

    private HashMap<String, String>  initCriticalTimes() {
        HashMap<String, String> criticalTimes = new HashMap<>();
        criticalTimes.put("start_time", "8:00");
        criticalTimes.put("end_time", "22:00");
        criticalTimes.put("lunch_start", "11:30");
        criticalTimes.put("lunch_end", "13:00");
        criticalTimes.put("dinner_start", "17:30");
        criticalTimes.put("dinner_end", "19:00");
        return criticalTimes;
    }

    private static int getWeek(int dateInt) {
        SimpleDateFormat weekFmt = new SimpleDateFormat("EEEE");
        Date date = new Date();
        date.setTime(dateInt);
        String weekStr = weekFmt.format(date);
        int weekInt = 0;
        switch (weekStr) {
            case "Tuesday":   weekInt = 1; break;
            case "Wednesday": weekInt = 2; break;
            case "Thursday":  weekInt = 3; break;
            case "Friday":    weekInt = 4; break;
            case "Saturday":  weekInt = 5; break;
            case "Sunday":    weekInt = 6; break;
            default: break;
        }
        return weekInt;
    }

    private String getTypes(UserAnswers userAnswersHolder) {
        List<String> types = Arrays.stream(
                ("Gardens,Parks,Museums,Observation_deck,Zoo,Themeparks,Neighbourhoods,Religious_Sites,Landmarks," +
                        "Historical_Sites,Island,Shopping_Malls,Bridges,Beaches").split("\\s*(,\\s*)+")
        ).filter(s -> (boolean) UserAnswers.getObjAttr(userAnswersHolder, s)).collect(Collectors.toList());

        return String.join(",", types).replace("_", " ");
    }

    private void solveService(Comparable<?> clientId) throws ParseException {
        List<com.travel_recommender.opta.Spot> spotArrangeList = new ArrayList<>();
        List<SpotSnippet> snippets = new ArrayList<>();
        List<TimeCapsule> capsules = new ArrayList<>();

        int days = (userAnswersHolder.getQnsLeavingTime() - userAnswersHolder.getQnsDepartureTime()) / (60 * 24);
        assert days > 0;
        List<Day> dayList = new ArrayList<>();
        long capsuleId = 0L;
        for (long i = 0L; i < days; ++i) {
            Day day = new Day(
                    i,
                    DateTime2Int(userAnswersHolder.getQnsDepartureTime(), i, criticalTimes.get("start_time")),
                    DateTime2Int(userAnswersHolder.getQnsDepartureTime(), i, criticalTimes.get("end_time")),
                    DateTime2Int(userAnswersHolder.getQnsDepartureTime(), i, criticalTimes.get("lunch_start")),
                    DateTime2Int(userAnswersHolder.getQnsDepartureTime(), i, criticalTimes.get("lunch_end")),
                    DateTime2Int(userAnswersHolder.getQnsDepartureTime(), i, criticalTimes.get("dinner_start")),
                    DateTime2Int(userAnswersHolder.getQnsDepartureTime(), i, criticalTimes.get("dinner_end")),
                    getWeek((int) (i + userAnswersHolder.getQnsDepartureTime() / (60 * 24))),
                    1,
                    30
            );
            // System.out.println(day);
            List<TimeCapsule> dayCapsules = new ArrayList<>();
            for (long j = 0L; j < day.getRelativeTimeCapsuleIndex(day.getEnd_time()); ++j, ++capsuleId) {
                TimeCapsule newCapsule = new TimeCapsule(
                        capsuleId,
                        1
                );
                capsules.add(newCapsule);
                dayCapsules.add(newCapsule);
            }
            day.setTimeCapsules(dayCapsules);
            dayList.add(day);
        }
        long snippetId = 0L;

        for (long i = 0L; i < clientIdToResultList.get(clientId).size() ; ++i) {
            Spot spot = clientIdToResultList.get(clientId).get((int) i);
            com.travel_recommender.opta.Spot spotArrange = new com.travel_recommender.opta.Spot(
                    i,
                    spot.getSpot_id(),
                    spot.getEst_duration(),
                    spot.getGourmet() > gourmetThreshold,
                    spot.getLgt(),
                    spot.getLtt(),
                    encodeTimeInWeek(spot.getOpen_times()),
                    encodeTimeInWeek(spot.getClose_times()),
                    spot.getElectiveScore(userAnswersHolder)
            );
            spotArrangeList.add(spotArrange);

            for (int j = 0; j < spotArrange.getRequiredTimeCapsuleNum(dayList.get(0)); ++j, ++snippetId) {
                SpotSnippet newSnippet = new SpotSnippet(
                        snippetId,
                        j,
                        spotArrange,
                        null
                );
                snippets.add(newSnippet);
            }
        }
        Solution problem = new Solution(
                0L,
                null,
                spotArrangeList,
                dayList,
                capsules,
                snippets
        );
        solverManager.solve(clientId, problem);
        clientIdToDayList.put(clientId, dayList);
    }
}