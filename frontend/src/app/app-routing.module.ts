import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewbookComponent } from './viewbook/viewbook.component';
import { AddbookComponent } from './addbook/addbook.component';

const routes: Routes = [ { path:'addbook', component:AddbookComponent }, { path:'viewbook', component:ViewbookComponent } ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
