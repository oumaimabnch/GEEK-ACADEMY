import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs/internal/BehaviorSubject';
import { User } from '../models/User';
import { TokenService } from './token.service';

@Injectable({
  providedIn: 'root',
})
export class AuthStateService {
  private userState = new BehaviorSubject<boolean>(this.token.isLoggedIn());
  userAuthState = this.userState.asObservable();

  constructor(public token: TokenService) {}

  setAuthState(value: boolean) {
    this.userState.next(value);
  }

  getCurrentUser(): User {
    const user: User = new User();
    user.id = parseInt(localStorage.getItem('id')||'');
    user.username = localStorage.getItem('username');
    user.email = localStorage.getItem('email');
    return user;
  }
}
