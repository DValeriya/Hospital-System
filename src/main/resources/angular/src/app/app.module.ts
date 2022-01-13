import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule, routingComponents} from './app-routing.module';

import {AppComponent} from './app.component';
import {ReactiveFormsModule} from "@angular/forms";
import {CoreModule} from "./core/core.module";
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {TokenInterceptor} from "./core/api/token.interceptor";
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { PasswordRecoveryComponent } from './password-recovery/password-recovery.component';
import { AddRegistryComponent } from './add-registry/add-registry.component';
import { AddDoctorComponent } from './add-doctor/add-doctor.component';
import { DoctorProfileComponent } from './doctor-profile/doctor-profile.component';


@NgModule({
  declarations: [
    AppComponent,
    routingComponents,
    AddRegistryComponent,
    AddDoctorComponent,
    DoctorProfileComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    CoreModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
