import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
import { Post } from '../../models/Post';
import { CommentService } from '../../services/comment.service';
import { PostsService } from '../../services/posts.service';
import { ToastService } from '../../toasts/toast-service';

@Component({
  selector: 'app-post-details',
  templateUrl: './post-details.component.html',
  styleUrls: ['./post-details.component.css'],
})
export class PostDetailsComponent implements OnInit {
  post: any;
  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private postsService: PostsService,
    private toastServive: ToastService
  ) {}
  ngOnInit(): void {
    // this.post = history.state;
    const id: number = this.activatedRoute.snapshot.params.id;
    console.log(id);

    this.postsService.getPost(id).subscribe(
      (response) => {
        console.log(response);
        this.post = response.data;
      },
      (error) => console.error(error)
    );
  }
}
