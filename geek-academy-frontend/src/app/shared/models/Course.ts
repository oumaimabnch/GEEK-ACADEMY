import { Session } from 'protractor';
import { Post } from './Post';

export interface Course {
  name: string;
  startingDate: Date;
  endingDate: Date;
  subjectName: string;
  sessions?: Session[];
}
