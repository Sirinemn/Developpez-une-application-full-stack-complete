import { Topic } from "./topic.interface";

export interface User {
    id?: number;
    email: string;
    name: string;
    topics?: Topic[];
    createdAt?: Date;
    updatedAt?: Date;
  }