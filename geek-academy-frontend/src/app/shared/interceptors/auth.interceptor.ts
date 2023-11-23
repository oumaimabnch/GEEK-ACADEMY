import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HttpErrorResponse,
  HttpResponse,
} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';
import { tap } from 'rxjs/operators';
import { TokenService } from '../services/token.service';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  constructor(public auth: TokenService, private router: Router) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    console.log('interceptor running');
    // Get the token from auth service.
    const authToken = this.auth.getToken();
    if (authToken) {
      // Clone the request to add the new header.
      const authReq = request.clone({
        headers: request.headers.set('Authorization', `Bearer ${authToken}`),
      });
      console.log('interceptor running with new headers');
      // send the newly created request
      return next.handle(authReq).pipe(
        tap(
          (event: HttpEvent<any>) => {
            if (event instanceof HttpResponse) {
              // Response wiht HttpResponse type
              console.log('TAP function', event);
            }
          },
          (err: any) => {
            console.log(err);
            if (err instanceof HttpErrorResponse) {
              if (err.status === 401) {
                localStorage.removeItem('token');
                this.router.navigate(['/']);
              }
            }
          }
        )
      );
    } else {
      console.log('interceptor without changes');
      return next.handle(request);
    }
  }
}
