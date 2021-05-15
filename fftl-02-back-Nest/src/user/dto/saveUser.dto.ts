import { Board } from '../../entities/board.entity';
import { Comment } from '../../entities/comment.entity';

export class SaveUserDto {
  uid: number;
  username: string;
  password: string;
  nickname: string;
  boards: Board[];
  comments: Comment[];
}
