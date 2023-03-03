import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Course } from 'src/app/model/course';
import { Professor } from 'src/app/model/professor';
import { AdminService } from 'src/app/service/admin.service';
import { CourseService } from 'src/app/service/course.service';

@Component({
  selector: 'app-assign-professor',
  templateUrl: './assign-professor.component.html',
  styleUrls: ['./assign-professor.component.css']
})
export class AssignProfessorComponent implements OnInit {

  professors: Professor[] = []
  courses: Course[] = []
  selectedCourse: Course | null = null;
  selectedProfessor: Professor | null = null;
  constructor(private adminService: AdminService,
    private courseService: CourseService) { }

  ngOnInit(): void {
    this.getProfessors();
    this.getCourses();
  }

  getProfessors() {
    this.adminService.getProfessors().subscribe(data => this.professors = data);
  }

  getCourses() {
    this.courseService.getCourses().subscribe(data => this.courses = data);
  }

  assignProfessor() {
    if (!this.selectedCourse || !this.selectedProfessor) {
      return;
    }
    else {
      this.adminService.assignCourseToProfessor(this.selectedCourse, this.selectedProfessor).subscribe((data) => console.log(data));
    }
  }

}
