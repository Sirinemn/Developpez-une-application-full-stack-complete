export interface Article {
  id?: number;
  titre: string;
  content: string;
  topicId: number;
  userId: number;
  topicName: string;
  userName: string;
  createdAt?: Date;
  updatedAt?: Date;
}
