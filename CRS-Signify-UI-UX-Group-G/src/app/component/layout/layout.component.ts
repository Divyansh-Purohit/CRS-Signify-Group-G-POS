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

  selectedTab: string = 'home';
  routes: Array<Route> = [
    {
      name: "/home",
      title: "Home"
    },
    {
      name: "/home",
      title: "Home"
    }, {
      name: "/home",
      title: "Home"
    }, {
      name: "/home",
      title: "Home"
    }, {
      name: "/home",
      title: "Home"
    }, {
      name: "/home",
      title: "Home"
    }, {
      name: "/home",
      title: "Home"
    },
  ]
  constructor() { }


  ngOnInit(): void {
  }

}
