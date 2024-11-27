import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TeskComponent } from './tesk.component';

const routes: Routes = [{ path: '', component: TeskComponent }];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TeskRoutingModule { }
