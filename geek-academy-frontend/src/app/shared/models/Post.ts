import { User } from './User';

export interface Post {
  id?: number;
  title: string;
  textBody: string;
  courseId?: number;
  jointFile?: File;
}
