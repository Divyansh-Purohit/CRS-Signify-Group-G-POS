import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Professor } from '../model/professor';

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private root = 'http://localhost:8080';

  constructor(private http: HttpClient) { }

  getAdmins(): Observable<any> {
    return this.http.get<any>(this.root + '/admins');
  }

  getProfessors(): Observable<Professor[]> {
    return this.http.get<Professor[]>(this.root + '/professors');
  }

  addAdmin(admin: any): Observable<any> {
    return this.http.post(`${this.root}` + '/addadmin', admin);
  }


}
