import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FooterComponent } from './layout/footer/footer.component';
import { HeaderComponent } from './layout/header/header.component';
import { MaterialModule } from 'src/app/material.module';
import { CriarTarefaComponent } from './components/criar-tarefa/criar-tarefa.component';
import { FormsModule } from '@angular/forms';
import { UpdateTarefaComponent } from './components/update-tarefa/update-tarefa.component';
import { ExcluirTarefaComponent } from './components/excluir-tarefa/excluir-tarefa.component';



@NgModule({
  declarations: [
    FooterComponent,
    HeaderComponent,
    CriarTarefaComponent,
    UpdateTarefaComponent,
    ExcluirTarefaComponent
  ],
  imports: [
    CommonModule,
    MaterialModule,
    FormsModule
  ],
  exports: [
    FooterComponent,
    HeaderComponent
  ]
})
export class SharedModule { }
