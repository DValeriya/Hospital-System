import { Injectable } from '@angular/core';
import {environment} from "../../../environments/environment";
import {HttpClient} from "@angular/common/http";
import {map, Observable, tap} from "rxjs";
import {User} from "../../models/user";
import {colors} from "@angular/cli/utilities/color";
import {Doctor} from "../../models/doctor";
import {Employer} from "../../models/employer";

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  private readonly userRegistrationEndpoint = `${environment.apiUrl}/user/create`
  private readonly registryAddEndpoint = `${environment.apiUrl}/registry/create`
  private readonly doctorAddEndpoint = `${environment.apiUrl}/doctor/create`

  constructor(private http: HttpClient) { }

  registerUser(user: User): Observable<any> {
    const birthDate = user.birthDate.toLocaleDateString("en-US");
    const data = {
      name: user.name,
      phoneNumber: user.phoneNumber,
      birthDate: birthDate,
      password: user.password,
      email: user.email,
      role: 'PATIENT'
    };

    return this.http.post(this.userRegistrationEndpoint, data).pipe(
      map((response: any) =>
        new User(response.login,
          response.name,
          response.email,
          response.phoneNumber,
          response.birthDate,
          '')
      ),
      tap((user: User) => console.log(user))
    );
  }

  registerEmployer(registry: Employer): Observable<any> {
    const birthDate = registry.birthDate.toLocaleDateString("en-US");
    const hiringDate = registry.hiringDate.toLocaleDateString("en-US");
    const data = {
      name: registry.name,
      phoneNumber: registry.phoneNumber,
      birthDate: birthDate,
      password: registry.password,
      email: registry.email,
      role: 'REGISTRY',
      employerData: {
        hiringDate: hiringDate,
        status: registry.status,
        startWorkingTime: registry.startWorkingTime + ':00',
        endWorkingTime: registry.endWorkingTime + ':00'
      }
    };

    console.log(hiringDate)
    console.log(data.employerData.startWorkingTime)
    console.log(data.employerData.endWorkingTime)

    return this.http.post(this.registryAddEndpoint, data).pipe(
      map((response: any) =>
        new Employer(response.login,
          response.name,
          response.email,
          response.phoneNumber,
          response.birthDate,
          '',
          response.employerData.hiringDate,
          response.employerData.status,
          response.employerData.startWorkingTime,
          response.employerData.endWorkingTime)
      ),
      tap((employer: Employer) => console.log(employer))
    );
  }

  registerDoctor(doctor: Doctor): Observable<any> {
    const birthDate = doctor.birthDate.toLocaleDateString("en-US");
    const hiringDate = doctor.hiringDate.toLocaleDateString("en-US");
    const data = {
      name: doctor.name,
      phoneNumber: doctor.phoneNumber,
      birthDate: birthDate,
      password: doctor.password,
      email: doctor.email,
      role: 'REGISTRY',
      employerData: {
        hiringDate: hiringDate,
        status: doctor.status,
        startWorkingTime: doctor.startWorkingTime + ':00',
        endWorkingTime: doctor.endWorkingTime + ':00',
        doctorData: {
          specialization: {
            specialization: doctor.specialization,
          },
          appointmentDuration: doctor.appointmentDuration + ':00'
        }
      }
    };

    return this.http.post(this.doctorAddEndpoint, data).pipe(
      map((response: any) =>
        new Doctor(response.login,
          response.name,
          response.email,
          response.phoneNumber,
          response.birthDate,
          '',
          response.doctor.hiringDate,
          response.doctor.status,
          response.doctor.startWorkingTime,
          response.doctor.endWorkingTime,
          response.doctor.specialization,
          response.doctor.appointmentDuration)
      ),
      tap((doctor: Doctor) => console.log(doctor))
    );
  }
}
