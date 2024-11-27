import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TeskRoutingModule } from './tesk-routing.module';
import { TeskComponent } from './tesk.component';
import { SharedModule } from '../shared/shared.module';
import { DragDropModule } from '@angular/cdk/drag-drop';


@NgModule({
  declarations: [
    TeskComponent
  ],
  imports: [
    CommonModule,
    TeskRoutingModule,
    SharedModule,
    DragDropModule
  ]
})
export class TeskModule { }
