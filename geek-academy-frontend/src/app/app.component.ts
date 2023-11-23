import { Component } from '@angular/core';
import { TokenService } from './shared/services/token.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  options = {
    autoClose: false,
    keepAfterRouteChange: false,
  };
  title = 'Geek-Academy';
  isLoggedIn = !this.auth.isLoggedIn();

  constructor(public auth: TokenService) {}
}
