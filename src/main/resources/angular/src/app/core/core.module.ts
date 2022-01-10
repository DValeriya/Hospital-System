import { NgModule } from '@angular/core';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {ForgotPasswordService} from "./api/forgot-password.service";
import {PasswordRecoveryService} from "./api/password-recovery.service";
import {AuthService} from "./api/auth.service";
import {RegistrationService} from "./api/registration.service";
import {TokenInterceptor} from "./api/token.interceptor";



@NgModule({
  declarations: [],
  imports: [
    HttpClientModule,
  ],
  providers: [
    ForgotPasswordService,
    PasswordRecoveryService,
    AuthService,
    RegistrationService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ]
})
export class CoreModule { }
