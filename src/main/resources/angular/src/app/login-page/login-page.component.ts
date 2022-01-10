import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {AuthService} from "../core/api/auth.service";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  loginForm: FormGroup | any;
  isSubmited = false;
  isLoading = false;
  error: any;

  constructor(private formBuilder: FormBuilder,
              private authService: AuthService,
              private router: Router) {
  }

  ngOnInit() {
    this.buildForm();
  }

  onSubmit() {
    this.isSubmited = true;
    this.error = null;

    if (this.loginForm.invalid) {
      return;
    }

    const formValue = this.loginForm.value;

    this.isLoading = true;

    this.authService.login(formValue.login, formValue.password)
      .subscribe({
        next: () => this.onSuccess(),
        error: () => this.onFailed()
      });
  }

  private onSuccess() {
    this.router.navigate(['/']);
  }

  private onFailed() {
    this.error = 'Invalid login or password';
    this.isLoading = false;
  }

  private buildForm() {
    this.loginForm = this.formBuilder.group({
      login: [null, Validators.required],
      password: [null, Validators.required],
    });
  }
}
