import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Course } from '../model/course';

@Injectable({
  providedIn: 'root'
})
export class CourseService {
  headers = new HttpHeaders().set('Content-Type', 'application/json').set('Access-Control-Allow-Origin', '*');
  private root = 'http://localhost:8080/';
  courses: Array<Course> | null = null;

  constructor(private http: HttpClient) { }

  getCourses(): Observable<Course[]> {
    return this.http.get<Course[]>(this.root + "listcourses", { headers: this.headers });
  }
  // Add a new course to the catalog
  addCourse(course: Course): Observable<any> {
    return this.http.post<any>(`${this.root}` + 'addcourse', course, { headers: this.headers });
  }

  // Remove an existing course from the catalog
  removeCourse(courseId: string): Observable<any> {
    let body = JSON.stringify({
      courseCode: courseId
    })
    return this.http.post<any>(`${this.root}/removecourse`, body, {
      headers: new HttpHeaders({ 'Content-Type': "application/json" })
    })
  }
}