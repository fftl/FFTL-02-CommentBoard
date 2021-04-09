import { Board } from 'src/entities/board.entity';
import { Comment } from 'src/entities/comments.entity';

export class SaveUserDto {
  uid: number;
  username: string;
  password: string;
  nickname: string;
  boards: Board[];
  comments: Comment[];
}
