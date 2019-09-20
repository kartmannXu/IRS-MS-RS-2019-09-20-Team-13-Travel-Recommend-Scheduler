import { Component, OnInit } from '@angular/core';
import { MessageService } from '../service/message.service';
import { interest_detail } from '../model/interest_detail';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';
import { UseranswerService } from '../service/useranswer.service';
import { UserAnswerViewModel } from '../model/UserAnswerViewModel';
  //这一页负责接收推荐景点的编号，形成card形式
  //待选择项目，1、是否需要删除 2、显示详细介绍可不可以用dialog形式
  //在最上加入"build your travel schedule！"按钮
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

    // this.response_now = 
    // {
    //       qnsDepartureTime : "2019-08-02",
    //       qnsLeavingTime : "2019-08-06",
    //       qnsCountryId:0,
    //       qnsKidElder: true,
    //       qnsFoodExpectation: true,
    //       qnsCultural: true,
    //       qnsCrowded: true,
    //       qnsBudget: 100,
    //       qnsSouveniers: false,
    //       qnsDowntown: false,
    //       qnsView: false,
    //       types: "Gardens,Parks,Museums,Observation_deck,Zoo,Themeparks,Neighbourhoods,Religious_Sites,Landmarks,Historical_Sites,Island,Shopping_Malls,Bridges,Activities,Beaches",

    // };

    // this.httpClientService.setResForRecommendation(this.response_now)
    // .subscribe(
    //   response_obj =>this.handleSuccessfulResponse(Response),
    //   );
    this.httpClientService.setResForRecommendation(this.response_now)
    .subscribe(
      val => {
          //console.log("Post call successful value returned in body", val);
          this.interest_list = val;
        },
        error => {
          console.log("Post call in error", error);
        },
        () => {
          //console.log("The Post observable is now completed.");
        }

        );
    //get infodata from back-end
    this.interest_list.length>0?this.interest_now = this.interest_list[0]:0;
  }

  handleSuccessfulResponse(Response)  {
      this.interest_list=Response;
//      this.messageService.add(this.interest_list[0].close_times);
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
