//地点属性类
export class interest_detail {

  spot_id:number;
    country_id:number;
    city_id:number;
    postal:number;
    spot_name:string;
    open_times:string;
    close_times:string;
    special_close_date:string;
    est_duration:number;
    score:number;
    ticket:number;
    imageUrl:string;

    // Coarse Spot Types
    gourmet:number;
    cultural:number;
    bustle:number;
    family:number;
    shopping:number;
    resort:number;
    downtown:number;
    // Concrete Spot Types
    gardens:number;
    parks:number;
    museums:number;
    observation_deck:number;
    zoo:number;
    themeparks:number;
    neighbourhoods:number;
    religious_Sites:number;
    landmarks:number;
    historical_Sites:number;
    island:number;
    shopping_Malls:number;
    bridges:number;
    activities:number;
    beaches:number;
    
    // introduction text
    introduction:string;
}

