import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators, FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { Course } from 'src/app/model/course';
import { CourseService } from 'src/app/service/course.service';

@Component({
  selector: 'app-add-course',
  templateUrl: './add-course.component.html',
  styleUrls: ['./add-course.component.css']
})
export class AddCourseComponent implements OnInit {

  addCourseForm: FormGroup = new FormGroup({
    one: new FormControl()
  }, { updateOn: 'blur' })

  course: Course = new Course('', '', '', 0, 0, 0, 0);
  constructor(
    private formBuilder: FormBuilder,
    private courseService: CourseService,
    private router: Router
  ) { }

  ngOnInit() {
    this.addCourseForm = this.formBuilder.group({
      courseName: ['', Validators.required],
      instructor: ['', Validators.required],
      description: ['', Validators.required],
      prerequisites: ['', Validators.required],
      startDate: ['', Validators.required],
      endDate: ['', Validators.required]
    });
  }

  addCourse() {
    // Get the values from the form
    const courseName = this.addCourseForm.controls['courseName'].value;
    const instructor = this.addCourseForm.controls['instructor'].value;
    const description = this.addCourseForm.controls['description'].value;
    const prerequisites = this.addCourseForm.controls['prerequisites'].value;
    const startDate = this.addCourseForm.controls['startDate'].value;
    const endDate = this.addCourseForm.controls['endDate'].value;

    // Call the course service to add the new course
    this.courseService.addCourse(this.course)
      .subscribe((res) => {
        console.log(res)
      });
    this.router.navigate(['/view-courses'])
  }

}