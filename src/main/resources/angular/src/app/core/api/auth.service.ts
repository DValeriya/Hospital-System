import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable, tap} from "rxjs";
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private readonly loginEndpoint = `${environment.apiUrl}/authorization/login`;
  private readonly tokenStorageKey = 'token';

  constructor(private http: HttpClient,
              private router: Router) { }

  login(login: string, password: string): Observable<any> {
    return this.http.post(this.loginEndpoint, { login, password }).pipe(
      tap((response: any) => {
        if (this.token !== null) {
          localStorage.removeItem(this.tokenStorageKey);
        }

        localStorage.setItem(this.tokenStorageKey, response.token);
      })
    );
  }

  logout(): void {
    localStorage.removeItem(this.tokenStorageKey);
    this.router.navigate(["/login"]);
  }

  get token() {
    return localStorage.getItem(this.tokenStorageKey);
  }
}
