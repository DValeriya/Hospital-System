import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {PasswordRecoveryService} from "../core/api/password-recovery.service";

@Component({
  selector: 'app-password-recovery',
  templateUrl: './password-recovery.component.html',
  styleUrls: ['./password-recovery.component.css']
})
export class PasswordRecoveryComponent implements OnInit {
  passwordRecoveryForm: FormGroup | any;
  isSubmited = false;
  error: any;
  token: String | undefined;

  constructor(private formBuilder: FormBuilder,
              private passwordRecoveryService: PasswordRecoveryService,
              private route: ActivatedRoute,
              private router: Router) {
  }

  ngOnInit() {
    this.buildForm();
    this.route.queryParams.subscribe(params => {
      this.token = params['token'];
    });
  }

  onSubmit() {
    this.isSubmited = true;
    this.error = null;

    if (this.passwordRecoveryForm.invalid) {
      return;
    }

    const formValue = this.passwordRecoveryForm.value;

    if (formValue.new_password !== formValue.confirm_password) {
      return;
    }

    this.passwordRecoveryService.recoverPassword(formValue.new_password, this.token)
      .subscribe({
        next: () => this.onSuccess(),
        error: () => this.onFailed()
      });
  }

  private onSuccess() {
    this.router.navigate(['/']);
  }

  private onFailed() {
    this.error = "Passwords doesn't match";
  }

  private buildForm() {
    this.passwordRecoveryForm = this.formBuilder.group({
      new_password: [null, Validators.required],
      confirm_password: [null, Validators.required],
    });
  }
}
