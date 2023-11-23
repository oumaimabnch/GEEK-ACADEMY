import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthStateService } from 'src/app/shared/services/auth-state.service';
import { TokenService } from 'src/app/shared/services/token.service';
import { ToastService } from 'src/app/shared/toasts/toast-service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css'],
})
export class NavbarComponent implements OnInit {
  isMenuCollapsed = true;
  isSignedIn: boolean = false;

  constructor(
    public router: Router,
    public stateUser: AuthStateService,
    public authService: AuthService,
    private token: TokenService,
    private toastService: ToastService
  ) {}

  ngOnInit(): void {
    this.stateUser.userAuthState.subscribe((val) => {
      this.isSignedIn = val;
    });
  }

  logout() {
    this.authService.logout().subscribe(
      (result) => {
        console.log(result);
        this.stateUser.setAuthState(false);
        this.token.removeToken();
        this.router.navigate(['login']);
        this.toastService.showSuccess('you are logged out');
      },
      (error) => {
        this.toastService.showDanger(error.message);
      },
      () => {
        localStorage.clear();
        this.isMenuCollapsed = true;
      }
    );
  }
}
