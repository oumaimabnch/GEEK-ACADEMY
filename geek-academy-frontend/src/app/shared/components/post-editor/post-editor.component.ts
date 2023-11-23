import { Component, OnInit } from '@angular/core';
import { PostsService } from '../../services/posts.service';
import { ActivatedRoute, Router } from '@angular/router';
import { ToastService } from '../../toasts/toast-service';
import { Post } from '../../models/Post';

@Component({
  selector: 'app-post-editor',
  templateUrl: './post-editor.component.html',
  styleUrls: ['./post-editor.component.css'],
})
export class PostEditorComponent implements OnInit {
  public post: Post = { title: '', textBody: '' };
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private postsService: PostsService,
    private toastServive: ToastService
  ) {}

  ngOnInit(): void {}

  submit() {
    this.postsService.addPost(this.post).subscribe(
      (result) => {
        this.toastServive.showSuccess('post successfully shared ');
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
