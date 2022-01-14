import {User} from "./user";
import {Time} from "@angular/common";

export class Employer extends User{
  hiringDate: Date;
  status: String;
  startWorkingTime: Time;
  endWorkingTime: Time;

  constructor(login: bigint, name: string, email: string, phoneNumber: string, birthDate: Date, password: string, hiringDate: Date, status: String, startWorkingTime: Time, endWorkingTime: Time) {
    super(login, name, email, phoneNumber, birthDate, password);
    this.hiringDate = hiringDate;
    this.status = status;
    this.startWorkingTime = startWorkingTime;
    this.endWorkingTime = endWorkingTime;
  }
}
