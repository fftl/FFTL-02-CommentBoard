import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Board } from '../entities/board.entity';
import { UserService } from '../user/user.service';
import { Repository } from 'typeorm';
import { SaveBoardDto } from './dto/saveBoard.dto';
import { UpdateBoardDto } from './dto/updateBoard.dto';

@Injectable()
export class BoardService {
  constructor(
    @InjectRepository(Board)
    private readonly boardRepository: Repository<Board>,
    private readonly userService: UserService,
  ) {}

  async saveBoard(saveBoardRequest: SaveBoardDto) {
    const user = this.userService.findOneByUid(saveBoardRequest.uid);

    const board = new Board(
      saveBoardRequest.title,
      saveBoardRequest.content,
      saveBoardRequest.nickname,
      saveBoardRequest.bregdate,
      await user,
    );

    return this.boardRepository.save(board);
  }

  async findBoards(): Promise<Board[]> {
    return this.boardRepository.find();
  }

  //bid 를 이용해 이와 연결된 user 정보까지 가져오는 것을 성공했다.
  async findOneBoardBid(bid: number): Promise<Board> {
    return this.boardRepository.findOne({
      relations: ['user'], //이 user를 가져온다?
      where: { bid : bid }, // 입력된 bid와 연관된?
    });
  }

  async updateBoard(bid: number, updateBoardDto: UpdateBoardDto) {
    return this.boardRepository.save({ bid: +bid, ...updateBoardDto });
  }

  async deleteBoard(bid: number){
    return this.boardRepository.delete(bid);
  }
}
