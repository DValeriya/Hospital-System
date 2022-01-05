import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  loginForm: FormGroup | any;
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

    if (this.loginForm.invalid) {
      return;
    }

    const formValue = this.loginForm.value;
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
    this.loginForm = this.formBuilder.group({
      login: [null, Validators.required],
      password: [null, Validators.required],
    });
  }
}
