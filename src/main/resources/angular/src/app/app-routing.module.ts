import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes} from "@angular/router";
import { LoginPageComponent } from "./login-page/login-page.component";
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import {ForgotPasswordComponent} from "./forgot-password/forgot-password.component";
import {PasswordRecoveryComponent} from "./password-recovery/password-recovery.component";
import { DoctorsPageComponent } from './doctors-page/doctors-page.component';

const routes: Routes = [
  { path: 'login', component: LoginPageComponent },
  { path: 'registration', component: RegistrationPageComponent },
  { path: 'forgot-password', component: ForgotPasswordComponent },
  { path: 'password-recovery', component: PasswordRecoveryComponent },
  { path: 'doctors', component: DoctorsPageComponent}
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [
  LoginPageComponent,
  RegistrationPageComponent,
  ForgotPasswordComponent,
  PasswordRecoveryComponent,
]
