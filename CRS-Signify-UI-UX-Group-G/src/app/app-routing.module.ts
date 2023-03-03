import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddAdminComponent } from './component/add-admin/add-admin.component';
import { AddCourseComponent } from './component/add-course/add-course.component';
import { AddProfessorComponent } from './component/add-professor/add-professor.component';
import { ApproveStudentComponent } from './component/approve-student/approve-student.component';
import { AssignProfessorComponent } from './component/assign-professor/assign-professor.component';
import { CalculateCpiComponent } from './component/calculate-cpi/calculate-cpi.component';
import { DropCourseComponent } from './component/drop-course/drop-course.component';
import { HomeComponent } from './component/home/home.component';
import { LogoutComponent } from './component/logout/logout.component';
import { ViewAdminsComponent } from './component/view-admins/view-admins.component';
import { ViewCoursesComponent } from './component/view-courses/view-courses.component';
import { ViewProfessorComponent } from './component/view-professor/view-professor.component';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'add-admin', component: AddAdminComponent },
  { path: 'add-course', component: AddCourseComponent },
  { path: 'add-professor', component: AddProfessorComponent },
  { path: 'approve-student', component: ApproveStudentComponent },
  { path: 'assign-professor', component: AssignProfessorComponent },
  { path: 'calculate-cpi', component: CalculateCpiComponent },
  { path: 'remove-course', component: DropCourseComponent },
  { path: 'view-courses', component: ViewCoursesComponent },
  { path: 'calculate-cpi', component: CalculateCpiComponent },
  { path: 'view-admins', component: ViewAdminsComponent },
  { path: 'view-professors', component: ViewProfessorComponent },
  { path: 'logout', component: LogoutComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
