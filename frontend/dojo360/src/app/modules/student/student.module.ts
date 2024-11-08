import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { StudentTableComponent } from './page/student-table/student-table.component';
import { RouterModule } from '@angular/router';
import { STUDENT_ROUTES } from './student.routing';
import { SidebarModule } from 'primeng/sidebar';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';



@NgModule({
  declarations: [
    StudentTableComponent
  ],
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forChild(STUDENT_ROUTES),

    SidebarModule,
    ButtonModule,
    CardModule,
    ToastModule
  ],
  providers: [MessageService],
})
export class StudentModule { }
