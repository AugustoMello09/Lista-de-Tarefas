import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Tarefa } from '../shared/model/tarefa.model';
import { HttpClient } from '@angular/common/http';
import { TarefaInsert } from '../shared/model/tarefaInsert.model';

@Injectable({
  providedIn: 'root'
})
export class TarefaService {

  baseUrl: string = environment.baseUrl;

  private tarefasSubject: BehaviorSubject<Tarefa[]> = new BehaviorSubject<Tarefa[]>([]);
  public tarefas$: Observable<Tarefa[]> = this.tarefasSubject.asObservable();

  constructor(private http: HttpClient) { }

  public listAll(): void {
    const url = `${this.baseUrl}/v1/tarefas`;
    this.http.get<Tarefa[]>(url).subscribe((tarefas) => {
      this.tarefasSubject.next(tarefas); 
    });
  }

  public create(tarefa: any): Observable<TarefaInsert> {
    const url = `${this.baseUrl}/v1/tarefas`;
    return this.http.post<Tarefa>(url, tarefa).pipe(
      tap((novaTarefa) => {
        this.tarefasSubject.next([...this.tarefasSubject.value, novaTarefa]);
      })
    );
  }

  public findById(id: any): Observable<Tarefa>{
    const url = `${this.baseUrl}/v1/tarefas/${id}`;
    return this.http.get<Tarefa>(url);
  }

  public delete(id: any): Observable<void>{
    const url = `${this.baseUrl}/v1/tarefas/${id}`;
    return this.http.delete<void>(url).pipe(
      tap(() => {
        const tarefas = this.tarefasSubject.value.filter((tarefa) => tarefa.id !== id);
        this.tarefasSubject.next(tarefas);
      })
    );
  }

  public updateTaskOrder(order: { sourceIndex: number, destinationIndex: number }): Observable<void> {
    const url = `${this.baseUrl}/v1/tarefas/move`;
    return this.http.post<void>(url, order);
  }

  public patchUpdate(id: number, fields: any): Observable<TarefaInsert> {
    const url = `${this.baseUrl}/v1/tarefas/${id}`;
    return this.http.patch<Tarefa>(url, fields).pipe(
      tap((tarefaAtualizada) => {
        const tarefas = this.tarefasSubject.value.map((tarefa) =>
          tarefa.id === id ? { ...tarefa, ...tarefaAtualizada } : tarefa
        );
        this.tarefasSubject.next(tarefas); 
      })
    );
  }
  
}
