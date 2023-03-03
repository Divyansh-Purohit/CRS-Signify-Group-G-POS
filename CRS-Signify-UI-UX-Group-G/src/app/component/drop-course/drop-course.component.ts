import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { Course } from 'src/app/model/course';
import { CourseService } from 'src/app/service/course.service';

@Component({
  selector: 'app-drop-course',
  templateUrl: './drop-course.component.html',
  styleUrls: ['./drop-course.component.css']
})
export class DropCourseComponent implements OnInit {

  courses: Array<Course> = []
  removeCourseForm: FormGroup = new FormGroup({
    one: new FormControl()
  })
  constructor(private formBuilder: FormBuilder,
    private router: Router,
    private courseService: CourseService) { }

  ngOnInit(): void {
    this.getCourses();
  }

  getCourses() {
    this.courseService.getCourses().subscribe((res: Array<Course>) => {
      this.courses = res;
    })
  }
  removeCourse(course: Course) {
    this.courses = this.courses.filter(c => c.courseCode != course.courseCode);
    this.courseService.removeCourse(course.courseCode).subscribe(res => console.log(res));
  }
}
