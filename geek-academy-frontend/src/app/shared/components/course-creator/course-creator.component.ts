import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Session } from 'protractor';
import { Course } from '../../models/Course';
import { CourseService } from '../../services/course.service';
import { PostsService } from '../../services/posts.service';
import { ToastService } from '../../toasts/toast-service';

@Component({
  selector: 'app-poll-editor',
  templateUrl: './course-creator.component.html',
  styleUrls: ['./course-creator.component.css'],
})
export class PollEditorComponent implements OnInit {
  sessions: Session[] = [];

  course: any;

  constructor(
    private courseService: CourseService,
    private router: Router,
    private toastServive: ToastService
  ) {}

  ngOnInit(): void {}
  /*
  public appendChoice(choice: any, days: any) {
    console.log(choice);
    this.choices.push({ name: choice });
    console.log(this.choices);

    var date = new Date();

    // add a day
    date.setDate(date.getDate() + parseInt(days));
    console.log(date);
  }
*/

  addSession() {}

  onSubmit() {
    var date = new Date();

    // add a day
    this.courseService.addCourse(this.course).subscribe(
      (result) => {
        this.toastServive.showSuccess('course successfully shared ');
        console.log(result);
      },
      (error) => {
        console.error(error);
        this.toastServive.showDanger(
          'Something went wrong \n check your data !'
        );
      },
      () => {
        this.router.navigate(['/']);
      }
    );
  }
}
