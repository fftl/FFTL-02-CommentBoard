import { Comment } from 'src/entities/comments.entity';
import { User } from 'src/entities/user.entity';

export class SaveBoardDto {
  readonly bid: number;
  readonly title: string;
  readonly bregdate: string;
  readonly nickname: string;
  readonly content: string;
  readonly user: User;
  readonly comments: Comment[];
}
