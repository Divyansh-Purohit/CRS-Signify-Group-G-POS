import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LayoutComponent } from './component/layout/layout.component';
import { ApproveStudentComponent } from './component/approve-student/approve-student.component';
import { AddProfessorComponent } from './component/add-professor/add-professor.component';
import { AddAdminComponent } from './component/add-admin/add-admin.component';
import { AssignProfessorComponent } from './component/assign-professor/assign-professor.component';
import { AddCourseComponent } from './component/add-course/add-course.component';
import { DropCourseComponent } from './component/drop-course/drop-course.component';
import { ViewCourseDetailsComponent } from './component/view-course-details/view-course-details.component';
import { CalculateCpiComponent } from './component/calculate-cpi/calculate-cpi.component';
import { ViewProfessorComponent } from './component/view-professor/view-professor.component';
import { ViewAdminsComponent } from './component/view-admins/view-admins.component';
import { LogoutComponent } from './component/logout/logout.component';
import { HomeComponent } from './component/home/home.component';
import { HttpClientModule } from '@angular/common/http';


@NgModule({
  declarations: [
    AppComponent,
    LayoutComponent,
    ApproveStudentComponent,
    AddProfessorComponent,
    AddAdminComponent,
    AssignProfessorComponent,
    AddCourseComponent,
    DropCourseComponent,
    ViewCourseDetailsComponent,
    CalculateCpiComponent,
    ViewProfessorComponent,
    ViewAdminsComponent,
    LogoutComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
