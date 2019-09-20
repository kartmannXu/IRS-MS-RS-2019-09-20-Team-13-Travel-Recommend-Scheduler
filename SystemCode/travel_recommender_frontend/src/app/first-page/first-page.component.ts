import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { question_box } from '../model/question_attr';//class
import { Answer } from '../model/Answer';//class
import { question } from '../model/question';//value
import { MessageService } from '../service/message.service';
import { HttpClientService } from '../service/http-client.service';
import { UserAnswerViewModel } from '../model/UserAnswerViewModel';
import { UseranswerService } from '../service/useranswer.service';
@Component({
  selector: 'app-first-page',
  templateUrl: './first-page.component.html',
  styleUrls: ['./first-page.component.css']
})
export class FirstPageComponent implements OnInit {

  constructor(
    private messageService: MessageService,
  	private router: Router,
    private httpClientService:HttpClientService,
    private useranswerService:UseranswerService,
  	) { }
//message is a function same to console.log

  questions = question;//从ts取出question数组
  question_now:question_box;//定义当前在处理的那个问题
  next_hidden = 0;//最后的下一页的隐藏属性
  response_new:UserAnswerViewModel = new UserAnswerViewModel;//给后台的数组


  ngOnInit() {
    this.questions[0].hidden = 0;
    this.question_now = this.questions[0]

  }

  //get question just one
  getquestion(id:number):void{
    this.question_now[0] = this.questions[id];
  }

//有点击则显示
  onDataChange(answerData: Answer) {
    for(var i=0;i<this.questions.length;i++) {
      if(answerData.id === this.questions[i].id){
        if(i != this.questions.length-1){
            this.questions[i+1].hidden = 0;
            this.question_now = this.questions[i+1];

            //将收到的数据填入数组 Answer应该是any属性，但如果是any属性，仍然需要根据判断进行强转
            switch(answerData.type)
            {
              case 1:
                this.response_new[this.questions[i].field] = answerData.select;
                // this.messageService.add('ssss');
                break;
              case 2:
                this.response_new[this.questions[i].field] = answerData.date;
                break;
              case 3:
                this.response_new[this.questions[i].field] = answerData.edit;
                break;
              case 4:
                this.response_new[this.questions[i].field] = 0;//临时的城市代码
                break;
              // case 5:
              //   this.response_new[this.questions[i].field] = answerData.combobox;
              //   break;
            }
        }
        else if(i == this.questions.length-1){
        this.next_hidden = 0;
        // this.messageService.add('come to the hidden');
        }
      }
    
    }
  }
  nextpage(): void {

    //Cause http only have post so It needs add service here

    this.useranswerService.setResponse(this.getResponse());
    // this.httpClientService.setRes(this.getResponse()).subscribe(data => {
    //       alert("Employee created successfully.");
    //     });//后方括号是返回的值
    this.router.navigate(['page2']);
  }
  getResponse():UserAnswerViewModel{
    
    return this.response_new
    //将属性名记录在类中，然后用objext['????']来调用
  }
}

