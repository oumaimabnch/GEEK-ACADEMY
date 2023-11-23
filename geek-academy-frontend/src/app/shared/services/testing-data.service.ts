import { Injectable } from '@angular/core';
import { Post } from '../models/Post';

@Injectable({
  providedIn: 'root',
})
export class TestingDataService {
  public listPost = [
    {
      id: 1,
      text:
        'Angular is one of the most popularly used frameworks with best-designed practices and tools for app development companies.',
    },
    {
      id: 2,
      text:
        'Angular encourages the developers to use components to split the user interface into reusable and different pieces',
    },
    {
      id: 3,
      text: 'what is your favourite PL ?',
      choices: [
        { id: 1, choiceName: 'java' },
        { id: 2, choiceName: 'Python' },
      ],
    },
  ];
  getlistPost() {
    return this.listPost;
  }
  constructor() {}
}
