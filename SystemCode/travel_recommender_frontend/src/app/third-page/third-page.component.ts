import { Component, OnInit } from '@angular/core';
import { MessageService } from '../service/message.service';
import { interest_detail } from '../model/interest_detail';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';
import { UseranswerService } from '../service/useranswer.service';
import { UserAnswerViewModel } from '../model/UserAnswerViewModel';
import { SpotSnippetViewModel } from '../model/SpotSnippetViewModel';  
import { schedule } from '../model/schedule'
import { ScheduleOption } from '../model/ScheduleOption'


@Component({
	selector: 'app-third-page',
	templateUrl: './third-page.component.html',
	styleUrls: ['./third-page.component.css']
})
export class ThirdPageComponent implements OnInit {
	interest_list:interest_detail[] = [];
	response_now:UserAnswerViewModel = new UserAnswerViewModel;
	isVisible = false;//control the visible of dialog
	isConfirmLoading = false;
	interest_now:interest_detail;


	//for html loading
	loadingFlag: number = 1;
	//schedule data
	spotSnippetViewModel_list : SpotSnippetViewModel[] = [];

	schedule_list : schedule[] = [];
	scheduleOption:ScheduleOption = new ScheduleOption;//for Option in schedule panel,date type need to init or it wont work


	//for status of opta
	resultOfStatus:string = "";
	constructor(
		private router:Router,
		private httpClientService:HttpClientService,
		private messageService: MessageService,
		private useranswerService: UseranswerService,
		) { 
		//获取上一页的信息 need to get from here or that will lost value
		this.response_now = useranswerService.getResponse();//get what set in last page
	}

	ngOnInit() {

		this.getAllData();

		}

		getRecommendation(MyObject:UserAnswerViewModel){
			return this.httpClientService.setResForRecommendation(MyObject).toPromise();
		}

		getStatus(clientid:number){
			return this.httpClientService.getSolverStatus(clientid).toPromise();
		}

		getResultList(clientid:number){
			return this.httpClientService.getResForResultList(clientid).toPromise();
		}

		getScheduleList(clientid:number){
			return this.httpClientService.getSchedule(clientid).toPromise();
		}

		FuncForinterest_now(){
			this.interest_list.length>0?this.interest_now = this.interest_list[0]:0;
		}

		FuncForinitscheduledatacombine(clientid:number){
			this.initscheduledatacombine();
		}

		delay(interval:number){
			return new Promise((resolve) => {
				setTimeout(resolve, interval);
			});
		}

		async getAllData() {
			const first = await this.getRecommendation(this.response_now);
			console.log(first);
			var n:number = 10;
			while(n>0)
			{
				n--;
				console.log("开始等待");
				await this.delay(20000);
				const second = await this.getStatus(this.response_now.id);
				console.log(second);
				if(second == "STOPPED")
				{
					n = -3;
				}
			}
			if(n = -3)
			{
				const fourth = await this.getResultList(this.response_now.id);
				this.interest_list = fourth as interest_detail[];
				
				console.log(fourth);
				const fifth = await this.getScheduleList(this.response_now.id);
				this.spotSnippetViewModel_list = fifth as SpotSnippetViewModel[];
				console.log(fifth);
			}
			await this.FuncForinterest_now();
			console.log("开始填写spot");
			await this.initscheduledatacombine();
			this.loadingFlag = 0;
		}


		handleSuccessfulResponse(Response)  {
			this.interest_list=Response;
			
		}

		initscheduledatacombine(){
			var begin_list:string[] = this.response_now.qnsDepartureTime.split("-");
			var begin_year:number = +begin_list[0]>-1?+begin_list[0]:2019;
			var begin_month:number = +begin_list[1]>0?+begin_list[1]-1:0;
			var begin_day:number = +begin_list[2]>-1?+begin_list[2]:1;
			console.log(begin_day)
			var end_list:string[] = this.response_now.qnsLeavingTime.split("-");
			var end_year:number = +end_list[0]>-1?+end_list[0]:2019;
			var end_month:number = +end_list[1]>0?+end_list[1]-1:0;
			var end_day:number = +end_list[2]>-1?+end_list[2]:1;
			//map the spotSnippetViewModel_list
			var scheduleListinFunc : schedule[] = [];
			//dont know how to init the object




			//you know what
			var n : number = 0;//counting
			let spot_id : number= -1;
			var mincapusule:number = 50;
			var maxcapusule:number = 0;
			var nDay:number = 0;
			let endtrigger:number = 1;
			let calculateduration:number = 30;
			for(let i = 0; i<this.spotSnippetViewModel_list.length;i++)
			{
				//if spotid change then endtrigger equal to 1
				if(spot_id != this.spotSnippetViewModel_list[i].spotId&& endtrigger == 1){
					spot_id = this.spotSnippetViewModel_list[i].spotId;
					mincapusule = this.spotSnippetViewModel_list[i].timeCapsuleId;
					maxcapusule = this.spotSnippetViewModel_list[i].timeCapsuleId;
					nDay = this.spotSnippetViewModel_list[i].day;
					endtrigger = -1;
				}
				if((spot_id != this.spotSnippetViewModel_list[i].spotId || i == this.spotSnippetViewModel_list.length -1) && endtrigger == -1){
					if (i != this.spotSnippetViewModel_list.length -1) {
						i -= 1;
					}
					var schedule_temp:schedule = {	Id: 2,
					Subject: "ChinaTown",
					StartTime: new Date(2019,8,6,9,30),
					EndTime: new Date(2019,8,6,12,30)
					};
					schedule_temp.Id = n;
					schedule_temp.Subject = this.spotSnippetViewModel_list[i].spotName;
					schedule_temp.StartTime = new Date(begin_year, begin_month,
						begin_day+nDay,
						8 + Math.floor(mincapusule/2), this.spotSnippetViewModel_list[i].est_duration%2*calculateduration
						);
					
					schedule_temp.EndTime = new Date(begin_year, +begin_month,
						begin_day+nDay,
						8 + Math.floor((mincapusule+this.spotSnippetViewModel_list[i].est_duration/calculateduration)/2),
						(mincapusule+this.spotSnippetViewModel_list[i].est_duration/calculateduration)%2*calculateduration
						);
					scheduleListinFunc.push(schedule_temp);
					n++;
					
					endtrigger = 1;
					mincapusule = 50;
					maxcapusule = 0;
					continue;
				}
				// else{
					if(mincapusule>this.spotSnippetViewModel_list[i].timeCapsuleId){
						mincapusule = this.spotSnippetViewModel_list[i].timeCapsuleId;
					}
					if(maxcapusule<this.spotSnippetViewModel_list[i].timeCapsuleId){
						maxcapusule = this.spotSnippetViewModel_list[i].timeCapsuleId;
					}
					if(nDay<this.spotSnippetViewModel_list[i].day){
						nDay = this.spotSnippetViewModel_list[i].day;
					}
				// }

			}

			this.scheduleOption.dayDate = new Date(begin_year,begin_month,begin_day);
			//console.log(this.scheduleOption.dayDate);
			this.scheduleOption.dayCount = end_day-begin_day +1;
			//console.log(this.scheduleOption.dayCount);
			this.schedule_list = scheduleListinFunc;
			console.log(this.schedule_list);



			}



		clickCard(interet:interest_detail): void {
			this.interest_now = interet;
			this.isVisible = true;
		}

		handleOk(): void {
			this.isConfirmLoading = true;
			setTimeout(() => {
				this.isVisible = false;
				this.isConfirmLoading = false;
			}, 3000);
		}

		handleCancel(): void {
			this.isVisible = false;
		}



	}
