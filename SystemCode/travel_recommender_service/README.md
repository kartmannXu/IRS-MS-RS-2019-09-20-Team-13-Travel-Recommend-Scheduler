Run backend
===

__prerequisite:__
安装 jdk8, 安装 maven3.6.1


```cmd
cd travel_recommender_model/ && mvn clean install
cd ..

cd travel-recommender/ && mvn clean install
cd ..

cd travel_recommender_service
mvn clean spring-boot:run
```

RESTful Endpoints (Back-Front interface) ([AppController.java](https://github.com/kartmannXu/MSRS-GP/blob/travelRecommender1/src/main/java/com/travelRecommender/service/AppController.java))
===

1. `localhost:8090/page2` -- __POST__
    
    - request input: [UserAnswerViewModel](https://github.com/kartmannXu/MSRS-GP/blob/2.0/travel_recommender_model/src/main/java/com/travel_recommender/model/UserAnswers.java) object.
    - return: modified `UserAnswerViewModel` object.
    
2. `localhost:8090/recommendation` -- __POST__

    - request input: `UserAnswerViewModel` object.
    - return: `List<`[Spot](https://github.com/kartmannXu/MSRS-GP/blob/2.0/travel_recommender_service/src/main/java/com/travel_recommender/model/Spot.java)`>`

3.  `localhost:8090/schedule` -- __GET__

    - return  `List<`[SpotSnippetViewModel](https://github.com/kartmannXu/MSRS-GP/blob/2.0/travel_recommender_service/src/main/java/com/travel_recommender/service/viewModel/SpotSnippetViewModel.java)`>`

### Appendix Component structure

![](https://github.com/kartmannXu/MSRS-GP/blob/travelRecommender1/image/component_structure.png)
