import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Board } from 'src/entities/board.entity';
import { User } from 'src/entities/user.entity';
import { Repository } from 'typeorm';
import { SaveUserDto } from './dto/saveUser.dto';

@Injectable()
export class UserService {
  constructor(
    @InjectRepository(User)
    private readonly userRepository: Repository<User>,
  ) {}

  async saveUser(saveUserDto: SaveUserDto) {
    return await this.userRepository.save(saveUserDto);
  }

  async findOne(username: string): Promise<User> {
    //User 또는 undefined 를 반환해줍니다.
    return this.userRepository.findOne({ username: username });
  }

  async findOneByUid(uid: number): Promise<User> {
    //User 또는 undefined 를 반환해줍니다.
    return this.userRepository.findOne({ uid: uid });
  }

}
