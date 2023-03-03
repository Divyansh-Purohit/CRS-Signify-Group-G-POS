import { Component, OnInit } from '@angular/core';

export type Route = {
  name: string,
  title: string,
}

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  selectedTab: string = 'Home';
  routes: Array<Route> = [
    {
      name: "/home",
      title: "Home"
    },
    {
      name: "/approve-student",
      title: "Approve Student"
    }, {
      name: "/add-professor",
      title: "Add Professor"
    }, {
      name: "/add-admin",
      title: "Add Admin"
    },
    {
      name: "/assign-professor",
      title: "Assign Professor"
    }, {
      name: "/add-course",
      title: "Add Course"
    }, {
      name: "/remove-course",
      title: "Remove Course"
    }, {
      name: "/view-courses",
      title: "View Courses"
    },
    // {
    //   name: "/calculate-cpi",
    //   title: "Calculate CPI"
    // },
    {
      name: "/view-professors",
      title: "View Professors"
    },
    {
      name: "/view-admins",
      title: "View Admins"
    },
    {
      name: "/logout",
      title: "Logout"
    }
  ]
  constructor() { }


  ngOnInit(): void {
  }

  changeRoute(route: Route) {
    this.selectedTab = route.title;
  }

}
