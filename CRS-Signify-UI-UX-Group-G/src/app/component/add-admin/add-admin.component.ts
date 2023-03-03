import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Admin, FormAdmin } from 'src/app/model/admin';
import { AdminService } from 'src/app/service/admin.service';

@Component({
  selector: 'app-add-admin',
  templateUrl: './add-admin.component.html',
  styleUrls: ['./add-admin.component.css']
})
export class AddAdminComponent implements OnInit {

  constructor(private adminService: AdminService,
    private router: Router) { }
  admin: FormAdmin = new FormAdmin("", "", "", "", new Date());

  ngOnInit(): void {
  }

  addAdmin() {
    this.adminService.addAdmin(this.admin).subscribe(res => console.log(res));
    this.router.navigate(['/view-admins']);
  }
}
