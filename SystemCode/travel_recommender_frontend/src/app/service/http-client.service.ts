import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserAnswerViewModel } from '../model/UserAnswerViewModel'
import { interest_detail } from '../model/interest_detail'
import { SpotSnippetViewModel } from '../model/SpotSnippetViewModel'

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  url:string = 'http://localhost:8090/';
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






//////////////////////////////////////////////////////////////////////////////
//for page 3
/////////////////////////////////////


  public setResForRecommendation(data:UserAnswerViewModel)
  {
    //可组成url
    //return this.httpClient.post<interest_detail[]>(this.url+data.id.toString()+'/recommendation',data);
    return this.httpClient.post(this.url+data.id.toString()+'/recommendation',data);
  }


  //after post to recommendation，get the status of opta it needs to while turn
  public getSolverStatus(clientid: number)
  {
    //可组成url
    return this.httpClient.get(this.url+clientid.toString()+'/solverStatus');
  }

  //  return result of spot detail its a list 
  public getResForResultList(clientid: number)
  {
    //可组成url
    return this.httpClient.get<interest_detail[]>(this.url+clientid.toString()+'/resultList');
  }

  //get schedule data
  public getSchedule(clientid: number)
  {
    //可组成url
    return this.httpClient.get<SpotSnippetViewModel[]>(this.url+clientid.toString()+'/recommendandschedule');
  }
}