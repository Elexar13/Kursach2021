
export class User {
  constructor(
    public userId?: number,
    public email?: string,
    public username?: string,
    public lastname?: string,
    public password?: string,
    public phoneNumber?: string,
    public isAdmin?: string
  ) {}
}
