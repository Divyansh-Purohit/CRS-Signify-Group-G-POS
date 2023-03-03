import { Component, OnInit } from '@angular/core';
import { Student } from 'src/app/model/student';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-approve-student',
  templateUrl: './approve-student.component.html',
  styleUrls: ['./approve-student.component.css']
})
export class ApproveStudentComponent implements OnInit {

  students: Student[] | null = null;
  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.getUnapprovedStudents();
  }

  getUnapprovedStudents() {
    this.adminService.getUnapprovedStudents().subscribe((data) => this.students = data);
  }

  approveStudent(student: Student) {
    this.students = this.students?.filter(s => s.studentId != student.studentId) ?? [];
    this.adminService.approveStudent(student).subscribe((data) => console.log(data));
  }
}
