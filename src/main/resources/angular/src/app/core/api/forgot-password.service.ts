import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ForgotPasswordService {
  private readonly forgotPasswordEndpoint = `${environment.apiUrl}/authorization/forgot-password`;

  constructor(private http: HttpClient) {
  }

  forgotPassword(email: String): Observable<any> {
    return this.http.post(this.forgotPasswordEndpoint, {email});
  }
}
