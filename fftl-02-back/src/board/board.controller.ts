import { Body, Controller, Get, Param, Post, UseGuards } from '@nestjs/common';
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
    return await this.boardService.getBoards();
  }

  @UseGuards(JwtAuthGuard)
  @Get("/:bid")
  async getOneBoard(@Param('bid') bid: number){
    return await this.boardService.getOneBoard(bid);
  }
}
