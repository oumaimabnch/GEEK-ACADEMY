import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthStateService } from 'src/app/shared/services/auth-state.service';
import { TokenService } from 'src/app/shared/services/token.service';
import { ToastService } from 'src/app/shared/toasts/toast-service';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  encapsulation: ViewEncapsulation.None,
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  errors = null;

  constructor(
    public router: Router,
    public fb: FormBuilder,
    public authService: AuthService,
    private token: TokenService,
    private authState: AuthStateService,
    private toastService: ToastService
  ) {
    this.loginForm = this.fb.group({
      username: [],
      password: [],
    });
  }

  ngOnInit() {}

  onSubmit() {
    this.authService.signin(this.loginForm.value).subscribe(
      (result) => {
        console.log(result);
        this.responseHandler(result);
        this.toastService.showSuccess('you are logged in');
        this.router.navigate(['/']);
      },
      (error) => {
        this.toastService.showDanger('Something went wrong !');
      },
      () => {
        this.authState.setAuthState(true);
        this.loginForm.reset();
      }
    );
  }

  // Handle response
  responseHandler(data: any) {
    this.token.handleData(data.token, data.username, data.email, data.id);
  }
}
