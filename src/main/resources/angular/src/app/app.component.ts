import { Component, OnInit, HostListener } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  public innerHeight: any;
  constructor() { }

  ngOnInit(): void {
    this.innerHeight = window.innerHeight;
  }
  title = 'angular';
  @HostListener('window:resize', ['$event'])
  onResize() {
    this.innerHeight = window.innerHeight;
  }
}
