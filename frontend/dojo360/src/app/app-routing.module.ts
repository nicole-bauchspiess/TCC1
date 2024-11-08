import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StudentTableComponent } from './modules/student/page/student-table/student-table.component';

const routes: Routes = [
  {
    path: 'student',
    loadChildren: () => import('./modules/student/student.module').then((m) => m.StudentModule)
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
