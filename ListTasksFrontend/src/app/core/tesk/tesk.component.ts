import { Component, OnInit } from '@angular/core';
import { Tarefa } from '../shared/model/tarefa.model';
import { CdkDragDrop, moveItemInArray } from '@angular/cdk/drag-drop';
import { MatDialog } from '@angular/material/dialog';
import { CriarTarefaComponent } from '../shared/components/criar-tarefa/criar-tarefa.component';
import { TarefaService } from '../service/tarefa.service';
import { ExcluirTarefaComponent } from '../shared/components/excluir-tarefa/excluir-tarefa.component';
import { UpdateTarefaComponent } from '../shared/components/update-tarefa/update-tarefa.component';

@Component({
  selector: 'app-tesk',
  templateUrl: './tesk.component.html',
  styleUrls: ['./tesk.component.css']
})
export class TeskComponent implements OnInit {

  public tarefas: Tarefa[] = [];

  public dragging: boolean = false;
  public draggedIndex: number | null = null;

  constructor(private dialog: MatDialog,
    private service: TarefaService
  ) { }

  ngOnInit(): void {
    this.service.listAll();
    this.service.tarefas$.subscribe((tarefas) => { 
      this.tarefas = tarefas;
    })
  }

  public deleteTarefa(id: number): void {
    this.dialog.open(ExcluirTarefaComponent, {
      data: { id: id }
    });
  }

  public open(): void {
    this.dialog.open(CriarTarefaComponent);
  }

  public atualizarTarefa(id: number): void {
    this.dialog.open(UpdateTarefaComponent, { data: { id: id } })
      .afterClosed().subscribe((updatedTask: Tarefa | null) => {
        if (updatedTask) {
          this.service.patchUpdate(id, updatedTask); 
        }
      });
  }

  public onDragStarted(index: number): void {
    this.dragging = true;
    this.draggedIndex = index;
  }
  public onDragEnded(): void {
    this.dragging = false;
    this.draggedIndex = null;
  }

  public drop(event: CdkDragDrop<Tarefa[]>): void {
    this.onDragEnded();
    const sourceIndex = event.previousIndex;
    const destinationIndex = event.currentIndex;
    
    moveItemInArray(this.tarefas, sourceIndex, destinationIndex);

    const orderUpdate = {
      sourceIndex: sourceIndex,
      destinationIndex: destinationIndex
    };

    this.service.updateTaskOrder(orderUpdate).subscribe(() => {
    }, () => {
      moveItemInArray(this.tarefas, destinationIndex, sourceIndex);
    });
  }

  public moveTarefa(sourceIndex: number, destinationIndex: number): void {
    moveItemInArray(this.tarefas, sourceIndex, destinationIndex);
    const orderUpdate = { sourceIndex: sourceIndex, destinationIndex: destinationIndex };
    this.service.updateTaskOrder(orderUpdate).subscribe(() => {
    }, () => {
      moveItemInArray(this.tarefas, destinationIndex, sourceIndex);
    }); 
  }

}
