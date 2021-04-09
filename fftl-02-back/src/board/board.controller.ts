import { Body, Controller, Post } from '@nestjs/common';
import { BoardService } from './board.service';
import { SaveBoardDto } from './dto/saveBoard.dto';

@Controller('board')
export class BoardController {
  constructor(private readonly boardService: BoardService) {}

  @Post('/saveBoard')
  async saveBoard(@Body() saveBoardDto: SaveBoardDto) {
    return this.boardService.saveBoard(saveBoardDto);
  }
}
