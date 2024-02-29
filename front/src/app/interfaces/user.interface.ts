import { Topic } from '../features/topics/interfaces/topic.interface';

export interface User {
  id?: number;
  email: string;
  name: string;
  topics?: Topic[];
  createdAt?: Date;
  updatedAt?: Date;
}
