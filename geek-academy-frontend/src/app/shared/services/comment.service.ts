import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { ToastService } from '../toasts/toast-service';
import { HandleError, HttpErrorHandler } from './http-error-handler.service';

@Injectable({
  providedIn: 'root',
})
export class CommentService {
  apiUrl = environment.apiUrl;
  postsUrl = this.apiUrl + '/posts/';

  articlesUrl = this.apiUrl + '/articles';

  private handleError: HandleError;
  constructor(
    private http: HttpClient,
    private errorHandler: HttpErrorHandler,
    private toastService: ToastService
  ) {
    this.handleError = errorHandler.createHandleError('PostsService');
  }

  public addComment(comment: any, postId: string): Observable<any> {
    return this.http
      .post<any>(this.postsUrl + postId + '/comments', comment)
      .pipe(catchError(this.handleError('addComment', comment)));
  }

  /** GET posts from posts endpoint */
  getComments(): Observable<Comment[]> {
    return this.http
      .get<Comment[]>(this.postsUrl)
      .pipe(catchError(this.handleError('getComments', [])));
  }
}
