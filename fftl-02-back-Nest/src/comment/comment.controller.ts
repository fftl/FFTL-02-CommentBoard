import { Body, Controller, Get, Param, Post, UseGuards } from '@nestjs/common';
import { JwtAuthGuard } from '../auth/guards/jwt-auth.guard';
import { CommentService } from './comment.service';
import { SaveCommentDto } from './dto/saveComment.dto';

@Controller('comment')
export class CommentController {
    constructor(
        private readonly commentService : CommentService,
        ){}


    @UseGuards(JwtAuthGuard)
    @Post()
    async saveComment(@Body() saveCommentRequest: SaveCommentDto){
        return this.commentService.saveComment(saveCommentRequest);
    }

    @UseGuards(JwtAuthGuard)
    @Get("/:bid")
    async getComments(@Param("bid") bid: number){
        return this.commentService.getComments(bid);
    }
    
}
