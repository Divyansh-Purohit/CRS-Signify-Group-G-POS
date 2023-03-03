import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { FormAdmin } from '../model/admin';
import { Course } from '../model/course';
import { FormProfessor, Professor } from '../model/professor';
import { Student } from '../model/student';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private root = 'http://localhost:8080';
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) { }

  getAdmins(): Observable<any> {
    return this.http.get<any>(this.root + '/admins');
  }

  getProfessors(): Observable<Professor[]> {
    return this.http.get<Professor[]>(this.root + '/professors');
  }

  addAdmin(admin: FormAdmin): Observable<any> {
    return this.http.post(`${this.root}` + '/addadmin', admin);
  }

  addProfessor(professor: FormProfessor): Observable<Professor> {
    return this.http.post<Professor>(this.root + '/addprofessor', professor, this.httpOptions);
  }

  assignCourseToProfessor(course: Course, professor: Professor): Observable<any> {
    let body = JSON.stringify({
      courseCode: course.courseCode,
      professorId: professor.userId,
    })
    return this.http.post(`${this.root}/assignprofessor`, body, this.httpOptions);
  }

  getUnapprovedStudents(): Observable<Student[]> {
    return this.http.get<Student[]>(this.root + '/unapprovedstudents');
  }

  approveStudent(student: Student): Observable<Student> {
    return this.http.post<Student>(this.root + "/approvebyid", student, this.httpOptions);
  }
}
