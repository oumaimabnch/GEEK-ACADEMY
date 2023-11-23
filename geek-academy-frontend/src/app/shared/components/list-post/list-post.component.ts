import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Post } from '../../models/Post';
import { PostsService } from '../../services/posts.service';

@Component({
  selector: 'app-list-post',
  templateUrl: './list-post.component.html',
  styleUrls: ['./list-post.component.css'],
})
export class ListPostComponent implements OnInit {
  public listPost:any[]=[];
  isMenuCollapsed = true;

  public daten: string="";
  constructor(
    private route: ActivatedRoute,
    private router: Router,

    private postsService: PostsService
  ) {}

  ngOnInit(): void {
    this.getposts();
  }

  getposts() {
    this.postsService.getPosts().subscribe(
      (result) => {
        console.log(result);
        this.listPost = result;
      },
      (error) => console.error(error)
    );
  }

}
