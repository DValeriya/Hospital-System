import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {tap} from "rxjs";
import {User} from "../../models/user";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private readonly userRegistrationEndpoint = `${environment.apiUrl}/user/create`

  constructor(private http: HttpClient) { }

  registerUser(user: User) {
    return this.http.post(this.userRegistrationEndpoint, user).pipe(
      tap((value: any) => {

      })
    );
  }
}
