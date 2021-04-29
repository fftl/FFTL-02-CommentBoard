import { Body, Controller, Delete, Get, Param, Patch, Post, Put, UseGuards } from '@nestjs/common';
import { JwtAuthGuard } from 'src/auth/guards/jwt-auth.guard';
import { UserService } from 'src/user/user.service';
import { BoardService } from './board.service';
import { SaveBoardDto } from './dto/saveBoard.dto';
import { UpdateBoardDto } from './dto/updateBoard.dto';

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
  @Patch("/:bid")
  async updateBoard(@Param('bid') bid: number, @Body() updateBoardDto: UpdateBoardDto) {
    return this.boardService.updateBoard(bid, updateBoardDto);
  }

  @UseGuards(JwtAuthGuard)
  @Delete("/:bid")
  async deleteBoard(@Param('bid') bid: number) {
    return this.boardService.deleteBoard(bid);
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
    return board;
  }
}
