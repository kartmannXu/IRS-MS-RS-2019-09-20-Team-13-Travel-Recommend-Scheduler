import { Component, OnInit, Input ,OnChanges, SimpleChange} from '@angular/core';
import { EventSettingsModel,DayService,TimeScaleModel} from '@syncfusion/ej2-angular-schedule';
import { schedule } from '../model/schedule'
import { ScheduleOption } from '../model/ScheduleOption'
@Component({
	selector: 'app-schedule-panel',
	providers: [DayService],
	templateUrl: './schedule-panel.component.html',
	styleUrls: ['./schedule-panel.component.css'],

})
export class SchedulePanelComponent implements  OnChanges {



	@Input() schedule_list: schedule[];
	@Input() scheduleOption: ScheduleOption;//for choose the right day

	constructor() { }
	//moniter
	changeLog: string[] = [];
	interval:number;

	selectedDate:Date = new Date();

	data:schedule[] = [];
	eventSettings:EventSettingsModel;
	timeScaleModel:TimeScaleModel;


	ngOnChanges(changes: {[propKey: string]: SimpleChange}) {

		if(this.schedule_list.length>0){
			this.InitData();
		}
	  }

	

	InitData() {
		this.interval = this.scheduleOption.dayCount;
		this.selectedDate = this.scheduleOption.dayDate;
		this.data = this.schedule_list;// year month day time and month begin at 0




		// this.selectedDate = new Date(2019,8,5);
		// this.interval = 5;
		// this.data = [{	Id: 1,
		// 	Subject: "ChinaTown",
		// 	StartTime: new Date(2019,8,5,9,30),
		// 	EndTime: new Date(2019,8,5,12,30),
		// },
		// {	Id: 2,
		// 	Subject: "ChinaTown",
		// 	StartTime: new Date(2019,8,6,9,30),
		// 	EndTime: new Date(2019,8,6,12,30)
		// },
		// {	Id: 3,
		// 	Subject: "ChinaTown",
		// 	StartTime: new Date(2019,8,7,9,30),
		// 	EndTime: new Date(2019,8,7,12,30)
		// },
		// {	Id: 4,
		// 	Subject: "ChinaTown",
		// 	StartTime: new Date(2019,8,8,9,30),
		// 	EndTime: new Date(2019,8,8,12,30)
		// },
		// {	Id: 5,
		// 	Subject: "ChinaTown",
		// 	StartTime: new Date(2019,8,9,9,30),
		// 	EndTime: new Date(2019,8,9,12,30)
		// }];

		this.timeScaleModel = {
			slotCount:2,
		},
		this.eventSettings = {
			dataSource: this.data,

			fields: {
				id: 'Id',
				subject: { name: 'Subject' },
				startTime: { name: 'StartTime' },
				endTime: { name: 'EndTime' },
			}
		}


	}



}

