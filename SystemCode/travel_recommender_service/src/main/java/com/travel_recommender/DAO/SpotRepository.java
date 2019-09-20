package com.travel_recommender.DAO;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.travel_recommender.model.Spot;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SpotRepository extends CrudRepository<Spot, Integer> {

    @Query("FROM Spot s WHERE s.Country_id=:Country_id AND " +
            "(s.Gourmet*:qnsGourmet+s.Cultural*:qnsCultural+s.Bustle*:qnsBustle+s.Family*:qnsFamily+s.Shopping*:qnsShopping" +
            "+s.Resort*:qnsResort+s.Downtown*:qnsDowntown)/(:qnsGourmet+:qnsCultural+:qnsBustle+:qnsFamily+:qnsShopping+" +
            ":qnsResort+:qnsDowntown)>=:scoreThreshold AND " +    // must have a total score s.t. spot types that higher then threshold
            "(s.Gardens=:Gardens AND :Gardens=1) OR (s.Parks=:Parks AND :Parks=1) OR (s.Museums=:Museums AND :Museums=1) OR " +
            "(s.Observation_deck=:Observation_deck AND :Observation_deck=1) OR (s.Zoo=:Zoo AND :Zoo=1) OR " +
            "(s.Themeparks=:Themeparks AND :Themeparks=1) OR (s.Neighbourhoods=:Neighbourhoods AND :Neighbourhoods=1) OR " +
            "(s.Religious_Sites=:Religious_Sites AND :Religious_Sites=true) OR (s.Landmarks=:Landmarks AND :Landmarks=1) OR " +
            "(s.Historical_Sites=:Historical_Sites AND :Historical_Sites=1) OR (s.Island=:Island AND :Island=1) OR " +
            "(s.Shopping_Malls=:Shopping_Malls AND :Shopping_Malls=1) OR (s.Bridges=:Bridges AND :Bridges=1) OR " +
            "(s.Beaches=:Beaches AND :Beaches=1) ORDER BY s.Score DESC")
    List<Spot> findSpot(@Param("Country_id") int Country_id,
                        @Param("qnsGourmet") float qnsGourmet,
                        @Param("qnsCultural") float qnsCultural,
                        @Param("qnsBustle") float qnsBustle,
                        @Param("qnsFamily") float qnsFamily,
                        @Param("qnsShopping") float qnsShopping,
                        @Param("qnsResort") float qnsResort,
                        @Param("qnsDowntown") float qnsDowntown,
                        @Param("scoreThreshold") double scoreThreshold,
                        @Param("Gardens") int Gardens,
                        @Param("Parks") int Parks,
                        @Param("Museums") int Museums,
                        @Param("Observation_deck") int Observation_deck,
                        @Param("Zoo") int Zoo,
                        @Param("Themeparks") int Themeparks,
                        @Param("Neighbourhoods") int Neighbourhoods,
                        @Param("Religious_Sites") int Religious_Sites,
                        @Param("Landmarks") int Landmarks,
                        @Param("Historical_Sites") int Historical_Sites,
                        @Param("Island") int Island,
                        @Param("Shopping_Malls") int Shopping_Malls,
                        @Param("Bridges") int Bridges,
                        @Param("Beaches") int Beaches
    );

    @Query("FROM Spot s WHERE s.Spot_id=:Spot_id")
    List<Spot> findSpotBySpot_id(@Param("Spot_id") int Spot_id);

    @Query("FROM Spot s WHERE s.Score>:scoreThreshold")
    List<Spot> findAllByScore(@Param("score") double scoreThreshold);
}
