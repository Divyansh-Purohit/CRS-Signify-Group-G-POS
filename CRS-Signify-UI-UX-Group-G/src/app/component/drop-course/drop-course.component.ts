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
  onSubmit() {
    // Get the values from the form
    const courseName = this.removeCourseForm.controls['courseName'].value;
    const instructor = this.removeCourseForm.controls['instructor'].value;
    const description = this.removeCourseForm.controls['description'].value;
    const prerequisites = this.removeCourseForm.controls['prerequisites'].value;
    const startDate = this.removeCourseForm.controls['startDate'].value;
    const endDate = this.removeCourseForm.controls['endDate'].value;

    // Call the course service to add the new course
    this.courseService.addCourse(courseName, instructor, description, prerequisites, startDate, endDate)
      .subscribe(() => {
        // Navigate back to the courses page after the course is added
        this.router.navigate(['/admin/courses']);
      });
  }
  removeCourse(course: Course) {
    this.courses = this.courses.filter(c => c.courseCode != course.courseCode);
    this.courseService.removeCourse(course.courseCode).subscribe(res => console.log(res));
  }
}
