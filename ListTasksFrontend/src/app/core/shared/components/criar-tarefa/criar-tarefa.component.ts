import { Component, OnInit } from '@angular/core';
import { TarefaInsert } from '../../model/tarefaInsert.model';
import { NgxSpinnerService } from 'ngx-spinner';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialogRef } from '@angular/material/dialog';
import { TarefaService } from 'src/app/core/service/tarefa.service';
import { HttpErrorResponse } from '@angular/common/http';


@Component({
  selector: 'app-criar-tarefa',
  templateUrl: './criar-tarefa.component.html',
  styleUrls: ['./criar-tarefa.component.css']
})
export class CriarTarefaComponent implements OnInit {

  public tarefa: TarefaInsert = {
    name: '',
    cost: 0,
    dueDate: ''
  }

  constructor(
    private service: TarefaService,
    private dialogRef: MatDialogRef<CriarTarefaComponent>,
    private snack: MatSnackBar,
    private spinner: NgxSpinnerService) { }

  ngOnInit(): void {
  }

  public create(): void {
    this.spinner.show();
    this.service.create(this.tarefa).subscribe(res => {
      this.tarefa = res;
      this.dialogRef.close();
      this.spinner.hide();
    }, (err: HttpErrorResponse) => { 
      this.addMessageError(err);
      this.spinner.hide();
    })
  }

  public message(msg: string): void {
    this.snack
      .open(`${msg}`, 'Error', {
        horizontalPosition: 'center',
        verticalPosition: 'bottom',
        duration: 8000
    })
  }

  public addMessageError(err: HttpErrorResponse) {
    if (err.status === 400) {
      this.message("Não é possível criar uma tarefa com o nome já existente ");
    }
  }

}
