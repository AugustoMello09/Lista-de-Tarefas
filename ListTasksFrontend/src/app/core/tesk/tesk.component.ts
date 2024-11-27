import { Component, OnInit } from '@angular/core';
import { Tarefa } from '../model/tarefa.model';
import { CdkDragDrop } from '@angular/cdk/drag-drop';

@Component({
  selector: 'app-tesk',
  templateUrl: './tesk.component.html',
  styleUrls: ['./tesk.component.css']
})
export class TeskComponent implements OnInit {

  public tarefas: Tarefa[] = [];

  public dragging: boolean = false;
  public draggedIndex: number | null = null;

  constructor() { }

  ngOnInit(): void {
  }

  public deleteTarefa(id: number): void {
    
  }

  public open(): void {
  
  }

  public atualizarTarefa(id: number): void {
   
  }

  public onDragStarted(index: number): void {
   
  }
  public onDragEnded(): void {
  
  }

  public drop(event: CdkDragDrop<Tarefa[]>): void {
    
  }

  public moveTarefa(sourceIndex: number, destinationIndex: number): void {
    
  }

}
