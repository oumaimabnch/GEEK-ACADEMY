import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './core/components/footer/footer.component';
import { NavbarComponent } from './core/components/navbar/navbar.component';
import { NotfoundComponent } from './core/components/notfound/notfound.component';
import { CoreModule } from './core/core.module';
import { SharedModule } from './shared/shared.module';
import { NgbModule, NgbToastModule } from '@ng-bootstrap/ng-bootstrap';
import { HomeModule } from './home/home.module';

import { CKEditorModule } from '@ckeditor/ckeditor5-angular';
import {
  HttpClient,
  HttpClientModule,
  HttpHandler,
  HTTP_INTERCEPTORS,
} from '@angular/common/http';
import { AuthInterceptor } from './shared/interceptors/auth.interceptor';
import { HttpErrorHandler } from './shared/services/http-error-handler.service';
import { NgbdToastGlobalModule } from './shared/toasts/toast-global.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FroalaEditorModule, FroalaViewModule } from 'angular-froala-wysiwyg';

@NgModule({
  declarations: [AppComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    CoreModule,
    SharedModule,
    HomeModule,
    NgbModule,
    CKEditorModule,
    HttpClientModule,
    NgbdToastGlobalModule,
    BrowserAnimationsModule,
  
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
    HttpErrorHandler,
    HttpClient,
  ],
  exports:[FroalaEditorModule, FroalaViewModule],
  bootstrap: [AppComponent],
})
export class AppModule {}
