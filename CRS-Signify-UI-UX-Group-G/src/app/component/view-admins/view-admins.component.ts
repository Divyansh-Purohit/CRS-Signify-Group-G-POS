import { Component, OnInit } from '@angular/core';
import { Admin } from 'src/app/model/admin';
import { AdminService } from 'src/app/service/admin.service';


@Component({
  selector: 'app-view-admins',
  templateUrl: './view-admins.component.html',
  styleUrls: ['./view-admins.component.css']
})
export class ViewAdminsComponent implements OnInit {

  admins: Admin[] = [];

  constructor(private adminService: AdminService) { }

  ngOnInit(): void {
    this.getAdmins();
  }

  getAdmins(): void {
    this.adminService.getAdmins()
      .subscribe(admins => this.admins = admins);
  }


}
