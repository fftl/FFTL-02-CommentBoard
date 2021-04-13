import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Board } from 'src/entities/board.entity';
import { Repository } from 'typeorm';
import { SaveBoardDto } from './dto/saveBoard.dto';

@Injectable()
export class BoardService {
  constructor(
    @InjectRepository(Board)
    private readonly boardRepository: Repository<Board>,
  ) {}

  async saveBoard(saveBoardDto: SaveBoardDto) {
    return this.boardRepository.save(saveBoardDto);
  }

  async getBoard(){
    return this.boardRepository.find();
  }
}
