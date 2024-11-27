import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeskRoutingModule } from './tesk-routing.module';
import { TeskComponent } from './tesk.component';
import { SharedModule } from '../shared/shared.module';


@NgModule({
  declarations: [
    TeskComponent
  ],
  imports: [
    CommonModule,
    TeskRoutingModule,
    SharedModule
  ]
})
export class TeskModule { }
