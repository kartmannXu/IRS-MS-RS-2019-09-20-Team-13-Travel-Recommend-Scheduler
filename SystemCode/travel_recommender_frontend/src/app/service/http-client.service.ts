import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserAnswerViewModel } from '../model/UserAnswerViewModel'
import { interest_detail } from '../model/interest_detail'

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  url:string = 'localhost:8090/';
  u_temp:UserAnswerViewModel = new UserAnswerViewModel
  constructor(
    private httpClient:HttpClient
  ) {      }

  //Same as setResForRecommendation
  //But it returns Key-value. So I need to change it to object
  public setRes(data:UserAnswerViewModel)
  {
    //可组成url
    return this.httpClient.post<UserAnswerViewModel>('http://localhost:8090/page2',data);

  }
  //0905 Found that I need to post data and analyse the return, instead of divide it into two function

  public setResForRecommendation(data:UserAnswerViewModel)
  {
    //可组成url
    return this.httpClient.post<interest_detail[]>('http://localhost:8090/recommendation',data);
  }
}