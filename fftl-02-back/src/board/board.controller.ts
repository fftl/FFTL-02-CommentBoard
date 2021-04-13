import { Body, Controller, Get, Post, UseGuards } from '@nestjs/common';
import { JwtAuthGuard } from 'src/auth/guards/jwt-auth.guard';
import { BoardService } from './board.service';
import { SaveBoardDto } from './dto/saveBoard.dto';

@Controller('board')
export class BoardController {
  constructor(private readonly boardService: BoardService) {}

  @UseGuards(JwtAuthGuard)
  @Post()
  async saveBoard(@Body() saveBoardDto: SaveBoardDto) {
    return this.boardService.saveBoard(saveBoardDto);
  }

  @UseGuards(JwtAuthGuard)
  @Get()
  async getBoard(){
    return this.boardService.getBoard();
  }

}
