import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Comment } from 'src/entities/comment.entity';
import { Repository } from 'typeorm';
import { SaveCommentDto } from './dto/saveComment.dto';

@Injectable()
export class CommentService {
    constructor(
        @InjectRepository(Comment)
        private readonly commentRepository: Repository<Comment>,
    ){}

    async saveComment(saveComment: SaveCommentDto){
        return this.commentRepository.save(saveComment);
    }


}
