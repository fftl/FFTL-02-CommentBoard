import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Board } from 'src/entities/board.entity';
import { Repository } from 'typeorm';
import { SaveBoardDto } from './dto/saveBoard.dto';
import { UpdateBoardDto } from './dto/updateBoard.dto';

@Injectable()
export class BoardService {
  constructor(
    @InjectRepository(Board)
    private readonly boardRepository: Repository<Board>,
  ) {}

  async saveBoard(saveBoardDto: SaveBoardDto) {
    return this.boardRepository.save(saveBoardDto);
  }

  async getBoards(): Promise<Board[]>{
    return this.boardRepository.find();
  }

  async getOneBoard(bid: number): Promise<Board>{
    return this.boardRepository.findOne(+bid);
  }

  async updateBoard(bid: number, updateBoardDto: UpdateBoardDto){
    return this.boardRepository.save({...updateBoardDto, bid:+bid});
  }
}
