import { Body, Controller, Get, Param, Post, UseGuards } from '@nestjs/common';
import { JwtAuthGuard } from 'src/auth/guards/jwt-auth.guard';
import { UserService } from 'src/user/user.service';
import { BoardService } from './board.service';
import { SaveBoardDto } from './dto/saveBoard.dto';

@Controller('board')
export class BoardController {
  constructor(
    private readonly boardService: BoardService,
    private readonly userService : UserService,
    ) {}

  @UseGuards(JwtAuthGuard)
  @Post()
  async saveBoard(@Body() saveBoardDto: SaveBoardDto) {
    return this.boardService.saveBoard(saveBoardDto);
  }

  @UseGuards(JwtAuthGuard)
  @Post()
  async updateBoard(@Body() saveBoardDto: SaveBoardDto) {
    return this.boardService.saveBoard(saveBoardDto);
  }


  @UseGuards(JwtAuthGuard)
  @Get()
  async getBoard(){
    return await this.boardService.findBoards();
  }

  @UseGuards(JwtAuthGuard)
  @Get("/:bid")
  async getOneBoard(@Param('bid') bid: number){
    const board = await this.boardService.findOneBoardBid(bid);
    // const user = await this.userService.findOneByBid(bid);
    // const getOneBoard = new GetOneBoard(board.title, board.content, board.nickname, board.bregdate, user.uid);

    return board;
  }
}
