import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RegistrationService} from "../core/api/registration.service";
import {Router} from "@angular/router";
import {Employer} from "../models/employer";
import {Doctor} from "../models/doctor";

@Component({
  selector: 'app-add-doctor',
  templateUrl: './add-doctor.component.html',
  styleUrls: ['./add-doctor.component.css']
})
export class AddDoctorComponent implements OnInit {

  registrationForm: FormGroup | any;
  isSubmited = false;
  error: any;

  States: any = ['WORK', 'ABSENT', 'DISMISSED', 'SICK', 'BUSINESS_TRIP', 'PERSONAL']

  constructor(private formBuilder: FormBuilder,
              private registrationService: RegistrationService,
              private router: Router) {
  }

  ngOnInit() {
    this.buildForm();
  }

  changeStatus(e : any) {
    this.registrationForm.status.setValue(e.target.value, {onlySelf: true})
  }

  onSubmit() {
    this.isSubmited = true;
    this.error = null;

    if (this.registrationForm.invalid) {
      return;
    }

    const formValue = this.registrationForm.value;
    const doctor = new Doctor(BigInt(0),
      formValue.fullname,
      formValue.email,
      formValue.phoneNumber,
      new Date(formValue.birthDate),
      formValue.password,
      new Date(formValue.hiringDate),
      formValue.status,
      formValue.startWorkingTime,
      formValue.endWorkingTime,
      formValue.specialization,
      formValue.appointmentDuration
      );

    this.registrationService.registerDoctor(doctor)
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
      hiringDate: [null, Validators.required],
      status: [''],
      startWorkingTime: [null, Validators.required],
      endWorkingTime: [null, Validators.required],
      specialization: [null, Validators.required],
      appointmentDuration: [null, Validators.required],
    });
  }
}
