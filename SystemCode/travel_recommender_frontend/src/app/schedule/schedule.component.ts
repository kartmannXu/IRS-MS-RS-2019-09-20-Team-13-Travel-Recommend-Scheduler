import { Component, OnInit, Input } from '@angular/core';
import { EventSettingsModel,DayService,TimeScaleModel} from '@syncfusion/ej2-angular-schedule';
import { schedule } from '../model/schedule'
import { ScheduleOption } from '../model/ScheduleOption'
@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css']
})
export class ScheduleComponent implements OnInit {


		@Input() schedule_list: schedule[];
	@Input() scheduleOption: ScheduleOption;//for choose the right day

  constructor() { }

  ngOnInit() {
  }



  //same need to deel with all the data 
}
