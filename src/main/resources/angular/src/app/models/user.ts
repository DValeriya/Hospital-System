export class User {
  readonly login: bigint;
  name: string;
  phoneNumber: string;
  email: string;
  birthDate: Date;
  password: string;

  constructor(login: bigint, name: string,email: string, phoneNumber: string, birthDate: Date, password: string) {
    this.login = login;
    this.name = name;
    this.email = email;
    this.phoneNumber = phoneNumber;
    this.birthDate = birthDate;
    this.password = password;
  }
}
