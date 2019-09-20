import { Component, OnInit ,Input} from '@angular/core';
import { interest_detail } from '../model/interest_detail';


@Component({
  selector: 'app-introduction',
  templateUrl: './introduction.component.html',
  styleUrls: ['./introduction.component.css']
})
export class IntroductionComponent implements OnInit {
	@Input() Interests: interest_detail;//当前打开的那个
  constructor() { }

  ngOnInit() {
  }

}
