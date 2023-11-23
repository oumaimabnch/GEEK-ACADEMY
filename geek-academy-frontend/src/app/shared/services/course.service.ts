import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { Course } from '../models/Course';
import { ToastService } from '../toasts/toast-service';
import { HandleError, HttpErrorHandler } from './http-error-handler.service';

@Injectable({
  providedIn: 'root',
})
export class CourseService {
  apiUrl = environment.apiUrl;
  coursesUrl = this.apiUrl + '/courses';

  private handleError: HandleError;

  constructor(
    private http: HttpClient,
    private errorHandler: HttpErrorHandler,
    private toastService: ToastService
  ) {
    this.handleError = errorHandler.createHandleError('PostsService');
  }

  /** GET courses from courses endpoint */
  getCourses(): Observable<Course[]> {
    return this.http
      .get<Course[]>(this.coursesUrl)
      .pipe(catchError(this.handleError('getCourses', [])));
  }

  /** GET course details by id from course endpoint */
  getCourse(id: number): Observable<any> {
    return this.http
      .get<any>(this.coursesUrl + `/${id}`)
      .pipe(catchError(this.handleError('getCourse', [])));
  }

  addCourse(course: Course): Observable<Course> {
    return this.http
      .post<Course>(this.coursesUrl, course)
      .pipe(catchError(this.handleError('addCourse', course)));
  }
}
