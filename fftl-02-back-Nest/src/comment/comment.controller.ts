import { Body, Controller, Get, Post, UseGuards } from '@nestjs/common';
import { JwtAuthGuard } from 'src/auth/guards/jwt-auth.guard';
import { BoardService } from 'src/board/board.service';
import { UserService } from 'src/user/user.service';
import { CommentService } from './comment.service';
import { SaveCommentDto } from './dto/saveComment.dto';

@Controller('comment')
export class CommentController {
    constructor(
        private readonly commentService : CommentService,
        ){}


    @UseGuards(JwtAuthGuard)
    @Post()
    async saveComment(@Body() saveCommentDto: SaveCommentDto){
        return this.commentService.saveComment(saveCommentDto);
    }
    
}
