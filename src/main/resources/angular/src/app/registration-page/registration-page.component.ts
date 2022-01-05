import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-registration-page',
  templateUrl: './registration-page.component.html',
  styleUrls: ['./registration-page.component.css']
})
export class RegistrationPageComponent implements OnInit {

  registrationForm: FormGroup | any;
  isSubmited = false;
  error: any;

  constructor(private formBuilder: FormBuilder,
              //private authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.buildForm();
  }

  onSubmit() {
    this.isSubmited = true;
    this.error = null;

    if (this.registrationForm.invalid) {
      return;
    }

    const formValue = this.registrationForm.value;
    //   this.authService.login(formValue.login, formValue.password)
    //     .subscribe(
    //       () => this.onSuccess(),
    //       () => this.onFailed());
  }

  private onSuccess() {
    this.router.navigate(['/']);
  }

  private onFailed() {
    this.error = 'Invalid login or password';
  }

  private buildForm() {
    this.registrationForm = this.formBuilder.group({
      fullname: [null, Validators.required],
      phoneNumber: [null, Validators.required],
      email: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

}
