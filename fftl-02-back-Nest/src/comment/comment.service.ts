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

        private readonly boardService: BoardService,
        private readonly userService: UserService,
    ){}

    async saveComment(saveComment: SaveCommentDto){

        const user = this.userService.findOneByUid(saveComment.uid);
        const board = this.boardService.findOneBoardBid(saveComment.bid);

        const comment = new Comment(
            saveComment.nickname,
            saveComment.comment,
            saveComment.cregdate,
            await user,
            await board
            )
            
        return this.commentRepository.save(comment);
    }
    
    //bid에 맞는 댓글들을 조회하여 보내주었다. 성공!!
    async getComments(bid : number): Promise<Comment[]> {
        //comments를 가져온다. commentRepository 를 사용하여,
        const comments = this.commentRepository.find({
            relations:["board"],    //board와 관계를 갖고 있고,
            where:{ board : {bid:bid} } //board의 bid 와 getComments의 변수 bid와 동일한 것만 가져온다.
        });
        return comments;
    }

}
