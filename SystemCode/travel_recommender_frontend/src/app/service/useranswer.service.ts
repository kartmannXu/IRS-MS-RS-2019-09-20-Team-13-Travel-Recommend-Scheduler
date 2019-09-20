import { Injectable } from '@angular/core';
import { UserAnswerViewModel } from '../model/UserAnswerViewModel';
import { Observable, of } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UseranswerService {
	Response:UserAnswerViewModel = new UserAnswerViewModel;
  constructor() { }

  setResponse(data:UserAnswerViewModel){
  	this.Response = data;
  }

  getResponse():UserAnswerViewModel{
  	return this.Response;
  }


}
