import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PasswordRecoveryService {
  private readonly passwordRecoveryEndpoint = `${environment.apiUrl}/authorization/reset-password`;

  constructor(private http: HttpClient) {
  }

  recoverPassword(password: String, token: String | undefined): Observable<any> {
    return this.http.post(this.passwordRecoveryEndpoint, {password, token});
  }
}
