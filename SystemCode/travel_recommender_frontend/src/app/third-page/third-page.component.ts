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
			var n:number = 10;
			while(n>0)
			{
				n--;
				await this.delay(20000);
				const second = await this.getStatus(this.response_now.id);

				if(second == "STOPPED")
				{
					n = -3;
				}
			}
			if(n = -3)
			{
				const fourth = await this.getResultList(this.response_now.id);
				this.interest_list = fourth as interest_detail[];
				await this.spotListDealFunc();




				console.log(fourth);
				const fifth = await this.getScheduleList(this.response_now.id);
				this.spotSnippetViewModel_list = fifth as SpotSnippetViewModel[];


				console.log(fifth);
			}
			await this.FuncForinterest_now();

			await this.initscheduledatacombine();
			this.loadingFlag = 0;
		}


		handleSuccessfulResponse(Response)  {
			this.interest_list=Response;
			
		}

		spotListDealFunc(){

			for(var nSpotIndex = 0;nSpotIndex<this.interest_list.length;nSpotIndex++){
				this.interest_list[nSpotIndex].introduction.replace("/n","");
				this.interest_list[nSpotIndex].introduction.replace("<br>","");
				this.interest_list[nSpotIndex].introduction.replace("[1]","");
				this.interest_list[nSpotIndex].introduction.replace("[2]","");
				this.interest_list[nSpotIndex].introduction.replace("[3]","");
				this.interest_list[nSpotIndex].introduction.replace("[4]","");
				this.interest_list[nSpotIndex].introduction.replace("[5]","");
				this.interest_list[nSpotIndex].addr = this.interest_list[nSpotIndex].addr +" " +this.interest_list[nSpotIndex].postal.toString();
			}
		}

   //计算天数差的函数，通用  
	   DateDiff(sDate1:string,  sDate2:string):number{    //sDate1和sDate2是xxxx-xx-xx格式  
		   var  aDate,  oDate1,  oDate2,  iDays  
		   aDate  =  sDate1.split("-")  
		   oDate1  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])    //转换为xx-xx-xxxx格式  
		   aDate  =  sDate2.split("-")  
		   oDate2  =  new  Date(aDate[1]  +  '-'  +  aDate[2]  +  '-'  +  aDate[0])  
		   var days :number =  oDate1 .getTime() -  oDate2 .getTime();//日期相差毫秒数
			var time = Math.floor(days / (1000 * 60 * 60 * 24));//毫秒数转天数
		   return  time
	   }  

		initscheduledatacombine(){

			var begin_list:string[] = this.response_now.qnsDepartureTime.split("-");
			var begin_year:number = +begin_list[0]>-1?+begin_list[0]:2019;
			var begin_month:number = +begin_list[1]>0?+begin_list[1]-1:0;
			var begin_day:number = +begin_list[2]>-1?+begin_list[2]:1;

			var end_list:string[] = this.response_now.qnsLeavingTime.split("-");
			var end_year:number = +end_list[0]>-1?+end_list[0]:2019;
			var end_month:number = +end_list[1]>0?+end_list[1]-1:0;
			var end_day:number = +end_list[2]>-1?+end_list[2]:1;


			var diffDay:number = this.DateDiff(this.response_now.qnsLeavingTime,this.response_now.qnsDepartureTime);

			//map the spotSnippetViewModel_list
			var scheduleListinFunc : schedule[] = [];
			//dont know how to init the object

			//build a list for day all 0
			var dayarray = new Array(); //先声明一维 
			for ( var i = 0; i < diffDay; i++) { //一维长度为day
				dayarray[i] = new Array(); //再声明二维 
				for ( var j = 0; j < 28; j++) { //二维长度为28
					dayarray[i][j] = 0; // 赋值
				}
			}

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
				if(spot_id != this.spotSnippetViewModel_list[i].spotId){
					//begin to find list
					spot_id = this.spotSnippetViewModel_list[i].spotId;
					for(let i = 0; i<this.spotSnippetViewModel_list.length;i++){
						if (spot_id == this.spotSnippetViewModel_list[i].spotId) {
							if(mincapusule>this.spotSnippetViewModel_list[i].timeCapsuleId){
								mincapusule = this.spotSnippetViewModel_list[i].timeCapsuleId;
							}
							if(maxcapusule<this.spotSnippetViewModel_list[i].timeCapsuleId){
								maxcapusule = this.spotSnippetViewModel_list[i].timeCapsuleId;
							}
							if(nDay<this.spotSnippetViewModel_list[i].day){
								nDay = this.spotSnippetViewModel_list[i].day;
							}
						}
					}

					var schedule_temp:schedule = {  Id: 2,
					Subject: "ChinaTown",
					StartTime: new Date(2019,8,6,9,30),
					EndTime: new Date(2019,8,6,12,30)
					};
					schedule_temp.Id = n;
					schedule_temp.Subject = this.spotSnippetViewModel_list[i].spotName;

					//占点为止
					var begin_point = 0;
					var end_point = 0;
					//if Time wrong delete it
					if(Math.floor((maxcapusule - this.spotSnippetViewModel_list[i].est_duration/calculateduration)/2)<0){
					// 	schedule_temp.StartTime = new Date(begin_year, begin_month,
					// 	begin_day+nDay,
					// 	8 + Math.floor((mincapusule)/2), (mincapusule)%2*calculateduration
					// 	);
					
					// schedule_temp.EndTime = new Date(begin_year, +begin_month,
					// 	begin_day+nDay,
					// 	8 + Math.floor((mincapusule + this.spotSnippetViewModel_list[i].est_duration/calculateduration)/2),
					// 	(mincapusule + this.spotSnippetViewModel_list[i].est_duration/calculateduration)%2*calculateduration
					// 	);
					begin_point = mincapusule;
					end_point = mincapusule + this.spotSnippetViewModel_list[i].est_duration/calculateduration;
					}
					else{
					// 	schedule_temp.StartTime = new Date(begin_year, begin_month,
					// 	begin_day+nDay,
					// 	8 + Math.floor((maxcapusule - this.spotSnippetViewModel_list[i].est_duration/calculateduration)/2), (maxcapusule - this.spotSnippetViewModel_list[i].est_duration/calculateduration)%2*calculateduration
					// 	);
					
					// schedule_temp.EndTime = new Date(begin_year, +begin_month,
					// 	begin_day+nDay,
					// 	8 + Math.floor((maxcapusule)/2),
					// 	(maxcapusule)%2*calculateduration
					// 	);

					end_point = maxcapusule;
					begin_point = maxcapusule - this.spotSnippetViewModel_list[i].est_duration/calculateduration;
					}

					//这素你从未见过的全新逻辑
					//首先判断下该景点及景点之后一小时有没有被占用
					let wrongFlag = 0;
					for(let nIndex = begin_point;nIndex<end_point+1;nIndex++){
						
						if(dayarray[nDay][nIndex] == 0){
							continue;
						}
						else{
							wrongFlag = 1;
						}
					}

					if(wrongFlag == 0){
						for(let nIndex = begin_point;nIndex<end_point+1;nIndex++){
							dayarray[nDay][nIndex] = 1;
						}
					}else{
						var anotherWrongFlag = 0;
						var newPosition = 0;
						//看看四天里相同位置有没有空缺
						for(let dayIndex = 0;dayIndex<diffDay;dayIndex++){
							for(let nIndex = begin_point;nIndex<end_point+1;nIndex++){
								if(dayarray[dayIndex][nIndex] == 0){
									continue;
								}
								else{
									anotherWrongFlag = 1;
								}
							}

							if(anotherWrongFlag == 0){
								//说明有空缺
								for(let nIndex = begin_point;nIndex<end_point+1;nIndex++){
									dayarray[dayIndex][nIndex] = 1;
								}
								nDay = dayIndex;
								newPosition = 1;
								break
							}
							anotherWrongFlag = 0;
						}

						if(newPosition == 0){
							//说明没有新位置，删
							for(var spotListIndex = 0; spotListIndex<this.interest_list.length;spotListIndex++){
								if(this.interest_list[spotListIndex].spot_id == spot_id){
									console.log("name:"+this.interest_list[spotListIndex].spot_name+"Id"+this.interest_list[spotListIndex].spot_id)
									this.interest_list.splice(spotListIndex,1);
									break;
								}
							}
							continue;
						}

					}
					//赋值
					schedule_temp.StartTime = new Date(begin_year, begin_month,
						begin_day+nDay,
						8 + Math.floor((begin_point)/2), (begin_point)%2*calculateduration
						);
					
					schedule_temp.EndTime = new Date(begin_year, +begin_month,
						begin_day+nDay,
						8 + Math.floor((end_point)/2),
						(end_point)%2*calculateduration
						);

					scheduleListinFunc.push(schedule_temp);



					n++;

					mincapusule = 50;
					maxcapusule = 0;
					nDay= 0;


				}

			}

			this.scheduleOption.dayDate = new Date(begin_year,begin_month,begin_day);
			//console.log(this.scheduleOption.dayDate);
			this.scheduleOption.dayCount = diffDay +1;
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
