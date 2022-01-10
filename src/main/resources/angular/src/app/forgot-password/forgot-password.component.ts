import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {ForgotPasswordService} from "../core/api/forgot-password.service";

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  forgotPasswordForm: FormGroup | any;
  isSubmited = false;
  error: any;

  constructor(private formBuilder: FormBuilder,
              private forgotPasswordService: ForgotPasswordService,
              private router: Router) {
  }

  ngOnInit() {
    this.buildForm();
  }

  onSubmit() {
    this.isSubmited = true;
    this.error = null;

    if (this.forgotPasswordForm.invalid) {
      return;
    }

    const formValue = this.forgotPasswordForm.value;
    this.forgotPasswordService.forgotPassword(formValue.email)
      .subscribe({
        next: () => this.onSuccess(),
        error: () => this.onFailed()
      });
  }

  private onSuccess() {
    this.router.navigate(['/']);
  }

  private onFailed() {
    this.error = "This email doesn't exist in the system";
  }

  private buildForm() {
    this.forgotPasswordForm = this.formBuilder.group({
      email: [null, Validators.required, Validators.email]
    });
  }
}
