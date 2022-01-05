import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { RouterModule, Routes} from "@angular/router";
import { LoginPageComponent } from "./login-page/login-page.component";
import { RegistrationPageComponent } from './registration-page/registration-page.component';

const routes: Routes = [
  { path: 'login', component: LoginPageComponent},
  { path: 'registration', component: RegistrationPageComponent}
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
export const routingComponents = [LoginPageComponent, RegistrationPageComponent]
