import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormProfessor } from 'src/app/model/professor';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-professor',
  templateUrl: './add-professor.component.html',
  styleUrls: ['./add-professor.component.css']
})
export class AddProfessorComponent implements OnInit {

  professor: FormProfessor = new FormProfessor('', '', '', '', new Date(), 3, '', '', '');

  constructor(private formBuilder: FormBuilder,
    private adminService: AdminService,
    private router: Router) { }

  ngOnInit() {

  }

  addProfessor() {
    this.adminService.addProfessor(this.professor)
      .subscribe(
        data => {
          console.log('Professor added successfully');
        }
      );

    this.router.navigate(['/view-professors'])
  }
}
