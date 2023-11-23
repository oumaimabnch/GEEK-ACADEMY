import { Time, WeekDay } from '@angular/common';

export interface Session {
  id?: number;
  startingTime: Time;
  endingTime: Time;
  dayOfWeek: WeekDay;
  isWeekly: boolean;
}
