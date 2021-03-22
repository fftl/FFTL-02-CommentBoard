import { Injectable } from '@nestjs/common';
import { UsersService } from '../users/users.service';
import { JwtService } from '@nestjs/jwt';
import { Users } from 'src/users/users.entity';

@Injectable()
export class AuthService {
  constructor(
    private usersService: UsersService,
    private jwtService: JwtService
    ) {}

  async validateUser(username: string, password: string): Promise<any> {
    const user = await this.usersService.findOne(username);
    if (user && user.passWord === password) {
      const { passWord, ...result } = user;
      return result;
    }
    return null;
  }

  async login(user: Users) {
    const payload = { username: user.userName, sub: user.userId};
    return {
      access_token: this.jwtService.sign(payload)
    }
  }
}