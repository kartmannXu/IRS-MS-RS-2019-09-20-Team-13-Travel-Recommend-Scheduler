import { Component, OnInit } from '@angular/core';
import { attrictions_type } from '../model/attrictions_type';//这里import要和之前同名
import { attrictions_type_detail } from '../model/attrictions_type_detail';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';//1
import { UserAnswerViewModel } from '../model/UserAnswerViewModel';
import { MessageService } from '../service/message.service';
import { UseranswerService } from '../service/useranswer.service';
import { Page2TypeCard } from '../model/Page2TypeCard'

@Component({
	selector: 'app-second-page',
	templateUrl: './second-page.component.html',
	styleUrls: ['./second-page.component.css']
})
export class SecondPageComponent implements OnInit {
	//attrictions_list = attrictions_type_detail;
	isSpinning: boolean = true;
	AllResponse:UserAnswerViewModel = new UserAnswerViewModel;//init at the beginning then it will work whenever a back-end exit
	attrictions_list:string[];
	page2TypeCardList:Page2TypeCard[] = [];
	//Picture list
	constructor(
		private router:Router,
		private httpClientService:HttpClientService,
		private messageService: MessageService,
		private useranswerService:UseranswerService,
		) { 
			this.AllResponse = useranswerService.getResponse();
		}

	ngOnInit() {
		//only use post
		this.AllResponse = 
		{
			qnsNatural : false,
			qnsDepartureTime : "2019-10-10",
			qnsLeavingTime : "2019-10-13",
			qnsCountryId:0,
			qnsKidElder: true,
			qnsFoodExpectation: true,
			qnsCultural: false,
			qnsCrowded: true,
			qnsBudget: 1000,
			qnsSouveniers: true,
			qnsDowntown: true,
			qnsView: false,
			types: "Gardens,Parks,Museums,Observation_deck,Zoo,Themeparks,Neighbourhoods,Religious_Sites,Landmarks,Historical_Sites,Island,Shopping_Malls,Bridges,Activities,Beaches",
			id:10,
		};

		this.httpClientService.setRes(this.AllResponse).subscribe(
				response =>this.handleSuccessfulResponse(response),
		);
		//why this first?

	}

	handleSuccessfulResponse(Response)  {
		
		this.AllResponse=Response;
		// this.messageService.add(this.AllResponse.types);

				//切成数组
		if(this.AllResponse.types === undefined) {
			this.attrictions_list = ["Museums"]
			this.page2TypeCardList = [{id:1,Type:"Museums",hiddenFlag:1,imgUrl:""}];
		}
		else {
			this.attrictions_list = this.AllResponse.types.split(",");
			for(let i = 0; i<this.attrictions_list.length;i++){
				var card_temp:Page2TypeCard = {id:1,Type:"Museums",hiddenFlag:1,imgUrl:""};
				card_temp.id = i;
				card_temp.Type = this.attrictions_list[i];
				card_temp.hiddenFlag = 0;
				card_temp.imgUrl = "../../assets/img/page1question/"+card_temp.Type+".jpg"
				this.page2TypeCardList.push(card_temp);
			}
		}
		this.isSpinning = false;
	}
	
	afterClose(removedTag: {}): void {
		this.attrictions_list = this.attrictions_list.filter(tag => tag !== removedTag);
	}


	cardClick(cardId:number) {
		this.page2TypeCardList[cardId].hiddenFlag = 1;
		this.attrictions_list = this.attrictions_list.filter(tag => tag !== this.page2TypeCardList[cardId].Type);
	}

	nextpage(): void {
		//组合type
		let result = this.attrictions_list.join(",")
		this.AllResponse.types = result;
		this.useranswerService.setResponse(this.AllResponse);
		this.router.navigate(['recommendation']);//it wont work when put it back the http
		//this.httpClientService.SetRes(this.getResponse()).subscribe(    );//后方括号是返回的值
		
	}

}
