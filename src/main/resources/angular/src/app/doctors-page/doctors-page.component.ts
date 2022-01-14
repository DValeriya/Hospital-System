import { Component, OnInit } from '@angular/core';
import { HttpClient} from '@angular/common/http';
import {User} from "../models/user";

@Component({
  selector: 'app-doctors-page',
  templateUrl: './doctors-page.component.html',
  styleUrls: ['./doctors-page.component.css']
})
export class DoctorsPageComponent implements OnInit {

  
  constructor() { }

  ngOnInit(): void {
  }

}
