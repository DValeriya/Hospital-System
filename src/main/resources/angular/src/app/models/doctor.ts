import {Employer} from "./employer";
import {Time} from "@angular/common";

export class Doctor extends Employer{
  specialization: string;
  appointmentDuration: Time;

  constructor(login: bigint, name: string, email: string, phoneNumber: string, birthDate: Date, password: string, hiringDate: Date, status: String, startWorkingTime: Time, endWorkingTime: Time, specialization: string, appointmentDuration: Time) {
    super(login, name, email, phoneNumber, birthDate, password, hiringDate, status, startWorkingTime, endWorkingTime);
    this.specialization = specialization;
    this.appointmentDuration = appointmentDuration;
  }
}
