export interface User {
    id: number;
    email: string;
    name: string;
    topics: string[];
    createdAt: Date;
    updatedAt?: Date;
  }