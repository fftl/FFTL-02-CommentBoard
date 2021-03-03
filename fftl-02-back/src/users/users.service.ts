import { Injectable } from '@nestjs/common';
import { InjectRepository } from '@nestjs/typeorm';
import { Repository } from 'typeorm';
import { Users } from './users.entity';

// This should be a real class/interface representing a user entity
@Injectable()
export class UsersService {
  constructor(
    @InjectRepository(Users)
    private readonly usersRepository: Repository<Users>
  ){}

  async findOne(userName: string): Promise<Users | undefined> {
    return await this.usersRepository.findOne({userName:userName});
  }
}