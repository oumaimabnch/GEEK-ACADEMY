export class User {
  id?: number | null;
  username?: string | null;
  firstName?: string;
  lastName?: string;

  email!: string | null;
  password!: string;
  /*birthDate: Date;
  joinDate: Date;
  profilePic: string;*/
  token?: string;
  constructor() {}
}
