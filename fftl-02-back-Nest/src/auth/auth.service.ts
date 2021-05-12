import { Injectable } from '@nestjs/common';
import { JwtService } from '@nestjs/jwt';
import { UserService } from 'src/user/user.service';
import * as bcrypt from 'bcrypt'; //bcrypt 사용시 추가

@Injectable()
export class AuthService {
  constructor(
    private readonly userService: UserService,
    private readonly jwtService: JwtService,
  ) {}

  async validateUser(username: string, password: string): Promise<any> {
    const user = await this.userService.findOne(username);
    //bcrypt 적용하였습니다.
    if (user && await bcrypt.compare(password, user.password)) {
      const { password, ...result } = user;
      return result;
    }
    let notfound : string = "not found..";
    return notfound;
  }

  async login(user: any) {
    if(user.username){ //유저가 존재한다면
      const payload = { username: user.username, uid: user.uid, nickname: user.nickname };
      return {
        access_token: this.jwtService.sign(payload),
      };
    } else { //유저가 존재하지 않는다면
      return user;
    }
  }
}
