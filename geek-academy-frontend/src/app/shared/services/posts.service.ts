import { HttpClient } from '@angular/common/http';
import { error } from '@angular/compiler/src/util';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Post } from '../models/Post';
import { ToastService } from '../toasts/toast-service';
import { HttpErrorHandler, HandleError } from './http-error-handler.service';

@Injectable({
  providedIn: 'root',
})
export class PostsService {
  apiUrl = environment.apiUrl;
  postsUrl = this.apiUrl + '/posts';

  private handleError: HandleError;

  constructor(
    private http: HttpClient,
    private errorHandler: HttpErrorHandler,
    private toastService: ToastService
  ) {
    this.handleError = errorHandler.createHandleError('PostsService');
  }

  /** GET posts from posts endpoint */
  getPosts(): Observable<Post[]> {
    return this.http
      .get<Post[]>(this.postsUrl)
      .pipe(catchError(this.handleError('getPosts', [])));
  }

  /** GET post details from post endpoint */
  getPost(id: number): Observable<any> {
    return this.http
      .get<any>(this.postsUrl + `/${id}`)
      .pipe(catchError(this.handleError('getPost', [])));
  }

  addPost(post: Post): Observable<Post> {
    return this.http
      .post<Post>(this.postsUrl, post)
      .pipe(catchError(this.handleError('addPost', post)));
  }
}
