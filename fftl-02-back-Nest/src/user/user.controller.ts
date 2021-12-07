import {
  Body,
  Request,
  Controller,
  Post,
  UseGuards,
  Get,
} from '@nestjs/common';
import { AuthService } from '../auth/auth.service';
import { JwtAuthGuard } from '../auth/guards/jwt-auth.guard';
import { LocalAuthGuard } from '../auth/guards/local-auth.guard';
import { SaveUserDto } from './dto/saveUser.dto';
import { UserService } from './user.service';
import * as bcrypt from 'bcrypt'; //bcrypt 사용시 추가

@Controller('user')
export class UserController {
  constructor(
    private readonly useService: UserService,
    private readonly authService: AuthService,
  ) {}

  @Post('/saveUser')
  async saveUser(@Body() saveUserRequest: SaveUserDto) {
    //bcrypt 적용
    saveUserRequest.password = await bcrypt.hash(saveUserRequest.password, 10);
    return this.useService.saveUser(saveUserRequest);
  }

  @UseGuards(LocalAuthGuard)
  @Post('/login')
  async login(@Request() req) {
    return this.authService.login(req.user);
  }

  @UseGuards(JwtAuthGuard)
  @Get('/profile')
  getProfile(@Request() req) {
    return req.user;
  }
}
