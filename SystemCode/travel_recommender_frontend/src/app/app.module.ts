import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { TopBarComponent } from './top-bar/top-bar.component';
//import { QuestionBoxComponent } from './question-box/question-box.component';


//blox material
import { MaterialModule } from '@blox/material';
import { NgZorroAntdModule, NZ_I18N, en_US } from 'ng-zorro-antd';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { registerLocaleData } from '@angular/common';
import en from '@angular/common/locales/en';
import { SecondPageComponent } from './second-page/second-page.component';
import { FirstPageComponent } from './first-page/first-page.component';
import { ThirdPageComponent } from './third-page/third-page.component';


//Web API
import { QuestionCardComponent } from './question-card/question-card.component';
import { MessagesComponent } from './messages/messages.component';
import { IntroductionComponent } from './introduction/introduction.component';
// import { QuestioncardComponent } from './questioncard/questioncard.component';

registerLocaleData(en);


@NgModule({
  declarations: [
    AppComponent,
    TopBarComponent,
    //QuestionBoxComponent,
    SecondPageComponent,
    FirstPageComponent,
    ThirdPageComponent,
    QuestionCardComponent,
    MessagesComponent,
    IntroductionComponent,
    // QuestioncardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    //blox
    MaterialModule,
    NgZorroAntdModule,
    FormsModule,
    BrowserAnimationsModule,
    HttpClientModule,
  ],
  providers: [{ provide: NZ_I18N, useValue: en_US }],
  bootstrap: [AppComponent]
})
export class AppModule { }
