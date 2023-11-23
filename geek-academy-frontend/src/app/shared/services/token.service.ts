import { Injectable } from '@angular/core';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root',
})
export class TokenService {
  private issuer = {
    login: 'http://127.0.0.1:8080/api/auth/login',
    register: 'http://127.0.0.1:8080/api/auth/student',
  };

  constructor() {}

  handleData(token: string, username: string, email: string, id: string) {
    localStorage.setItem('id', id);
    localStorage.setItem('token', token);
    localStorage.setItem('username', username);
    localStorage.setItem('email', email);
  }

  getToken() {
    return localStorage.getItem('token');
  }

  // Verify the token
  isValidToken(): any {
    const token = this.getToken();
    return token ? true : false;
  }

  payload(token: string) {
    const jwtPayload = token.split('.')[1];
    return JSON.parse(atob(jwtPayload));
  }

  // User state based on valid token
  isLoggedIn() {
    console.log(this.isValidToken());
    return this.isValidToken();
  }

  // Remove token
  removeToken() {
    localStorage.removeItem('token');
  }
}
