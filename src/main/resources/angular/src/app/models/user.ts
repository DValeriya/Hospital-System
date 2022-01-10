export class User {
  readonly login: bigint;
  fullname: string;
  phoneNumber: string;
  birthDay: string;
  password: string;

  constructor(login: bigint, fullname: string, phoneNumber: string, birthDay: string, password: string) {
    this.login = login;
    this.fullname = fullname;
    this.phoneNumber = phoneNumber;
    this.birthDay = birthDay;
    this.password = password;
  }
}
