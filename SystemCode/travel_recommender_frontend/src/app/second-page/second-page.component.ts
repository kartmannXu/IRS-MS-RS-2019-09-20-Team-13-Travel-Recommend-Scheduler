import { Component, OnInit } from '@angular/core';
import { attrictions_type } from '../model/attrictions_type';//这里import要和之前同名
import { attrictions_type_detail } from '../model/attrictions_type_detail';
import { HttpClientService } from '../service/http-client.service';
import { Router } from '@angular/router';//1
import { UserAnswerViewModel } from '../model/UserAnswerViewModel';
import { MessageService } from '../service/message.service';
import { UseranswerService } from '../service/useranswer.service';
@Component({
  selector: 'app-second-page',
  templateUrl: './second-page.component.html',
  styleUrls: ['./second-page.component.css']
})
export class SecondPageComponent implements OnInit {
  //attrictions_list = attrictions_type_detail;

  AllResponse:UserAnswerViewModel = new UserAnswerViewModel;//init at the beginning then it will work whenever a back-end exit
  attrictions_list:string[];
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
    // this.AllResponse = 
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
    //       types: "Bridges",

    // };
    //console.log(this.AllResponse)
    this.httpClientService.setRes(this.AllResponse).subscribe(
        response =>this.handleSuccessfulResponse(response),
    );
    //why this first?

  }

  handleSuccessfulResponse(Response)  {
    
    this.AllResponse=Response;
    //this.messageService.add(this.AllResponse.types);

        //切成数组
    if(this.AllResponse.types === undefined) {
      this.attrictions_list = ["Museums"]
    }
    else {
      this.attrictions_list = this.AllResponse.types.split(",");
    }

  }
  
  afterClose(removedTag: {}): void {
    this.attrictions_list = this.attrictions_list.filter(tag => tag !== removedTag);
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
