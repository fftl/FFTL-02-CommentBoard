import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { BoardService } from 'src/board/board.service';
import { Comment } from 'src/entities/comment.entity';
import { UserService } from 'src/user/user.service';
import { Repository } from 'typeorm';
import { SaveCommentDto } from './dto/saveComment.dto';

@Injectable()
export class CommentService {
    constructor(
        @InjectRepository(Comment)
        private readonly commentRepository: Repository<Comment>,

        private readonly boardService : BoardService,
        private readonly userService : UserService,
    ){}

    async saveComment(saveComment: SaveCommentDto){
        return this.commentRepository.save(saveComment);

    }
}
