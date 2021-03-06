import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {RegistrationService} from "../core/api/registration.service";
import {User} from "../models/user";

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
              private registrationService: RegistrationService,
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
    const user = new User(BigInt(0),
      formValue.fullname,
      formValue.email,
      formValue.phoneNumber,
      new Date(formValue.birthDate),
      formValue.password);

    this.registrationService.registerUser(user)
      .subscribe({
        next: () => this.onSuccess(),
        error: () => this.onFailed()
      });
  }

  private onSuccess() {
    this.router.navigate(['/'])
  }

  private onFailed() {
    this.error = 'Invalid login or password';
  }

  private buildForm() {
    this.registrationForm = this.formBuilder.group({
      fullname: [null, Validators.required],
      birthDate: [null, Validators.required],
      phoneNumber: [null, Validators.required],
      email: [null, Validators.required],
      password: [null, Validators.required],
    });
  }

}
