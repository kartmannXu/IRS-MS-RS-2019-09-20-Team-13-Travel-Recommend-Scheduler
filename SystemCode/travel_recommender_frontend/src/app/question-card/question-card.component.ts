import { Component, OnInit, Input, Output,EventEmitter} from '@angular/core';
import { Answer } from '../model/Answer';//这里import要和之前同名
import { question_box } from '../model/question_attr';//class
import { MessageService } from '../service/message.service';
// import differenceInCalendarDays from 'date-fns/difference_in_calendar_days';
@Component({
  selector: 'app-question-card',
  templateUrl: './question-card.component.html',
  styleUrls: ['./question-card.component.css']
})
export class QuestionCardComponent implements OnInit {
	Answer_box:Answer = new Answer;
	@Input() questions: question_box;
  //然后就可以在方法里 emit 结果了
  @Output() dataChange = new EventEmitter<Answer>();
  // listOfOption = ['Activities','Beaches','Island','Boardwalks','Bridges','Observation_deck','Gardens','Landmarks','Historic_Sites','Museums','Neighbourhoods','Parks','Religious_Sites','Shopping_Malls','Themeparks','Zoo'];
  // listOfSelectedValue: string[] = [];
  // today = new Date();
  //以下是尝试，定义一个hidden值起值可等于input
  constructor(
    private messageService: MessageService,
    ) { }
  	
  // onInputChange(): void {
  //   this.dataChange.emit(this.itemdata);
  // }


  ngOnInit() {


  }

  radiochange(radioValue:number) {
    if(radioValue == 1)
    {
      this.Answer_box.select = true;
    }
    else{
      this.Answer_box.select = false;
    }
    
    this.Answer_box.id = this.questions.id;//1.checkbox 2.date 3.edit 4.dropbox
    this.Answer_box.type = 1;
    this.dataChange.emit(this.Answer_box);
  }
  dateChange(result: Date){

     var date1:number =result.getFullYear();
     var date2 :number=result.getMonth()+1;
     var date3:number=result.getDate();
     var date_string:string=date1+ "-"+date2 + "-" +date3;
    this.Answer_box.date = date_string;
    this.Answer_box.id = this.questions.id;
    this.Answer_box.type = 2;
    this.dataChange.emit(this.Answer_box);
  }
  inputChange(result:number){
    this.Answer_box.edit = result;
    this.Answer_box.id = this.questions.id;
    this.Answer_box.type = 3;
    this.dataChange.emit(this.Answer_box);
  }
  dropboxChange(result:string){
    this.Answer_box.combobox = result;
    this.Answer_box.id = this.questions.id;
    this.Answer_box.type = 4;
    this.dataChange.emit(this.Answer_box);

  }


  // MultiselectChange(result_list: string[]){
    
  //   var result = result_list.join(",")
  //   this.Answer_box.combobox = result;
  //   this.Answer_box.id = this.questions.id;
  //   this.Answer_box.type = 5;
  //   this.dataChange.emit(this.Answer_box);
  // }

  // isNotSelected(value: string): boolean {
  //   return this.listOfSelectedValue.indexOf(value) === -1;
  // }
  // disabledDate = (current: Date): boolean => {
  //   // Can not select days before today and today
  //   // return differenceInCalendarDays(current, this.today) > 0;
  // };
}

