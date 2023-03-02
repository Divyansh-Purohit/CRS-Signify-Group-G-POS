import { Component, OnInit } from '@angular/core';
import { Professor } from 'src/app/model/professor';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-view-professor',
  templateUrl: './view-professor.component.html',
  styleUrls: ['./view-professor.component.css']
})
export class ViewProfessorComponent implements OnInit {

  professors: Professor[] = [];

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.getProfessor();
  }

  getProfessor(): void {
    this.adminService.getProfessors()
      .subscribe(professors => this.professors = professors);
  }

}
