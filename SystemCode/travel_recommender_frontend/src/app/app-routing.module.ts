import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FirstPageComponent }      from './first-page/first-page.component';
import { SecondPageComponent }      from './second-page/second-page.component';
import { ThirdPageComponent }      from './third-page/third-page.component';


const routes: Routes = [
  { path: '', redirectTo: '/page1', pathMatch: 'full' },
  { path: 'page1', component: FirstPageComponent },
  { path: 'page2', component: SecondPageComponent },
  { path: 'recommendation', component: ThirdPageComponent },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
