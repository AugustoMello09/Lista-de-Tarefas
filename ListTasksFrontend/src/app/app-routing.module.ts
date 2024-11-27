import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: '', loadChildren: () => import('./core/home/home.module').then(m => m.HomeModule) },
  { path: 'tesk', loadChildren: () => import('./core/tesk/tesk.module').then(m => m.TeskModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
