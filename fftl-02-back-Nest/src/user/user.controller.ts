import {
  Body,
  Request,
  Controller,
  Post,
  UseGuards,
  Get,
  Inject,
  forwardRef,
} from '@nestjs/common';
import { AuthService } from 'src/auth/auth.service';
import { JwtAuthGuard } from 'src/auth/guards/jwt-auth.guard';
import { LocalAuthGuard } from 'src/auth/guards/local-auth.guard';
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
  async saveUser(@Body() saveUserDto: SaveUserDto) {
    //bcrypt 적용
    saveUserDto.password = await bcrypt.hash(saveUserDto.password, 10);
    return this.useService.saveUser(saveUserDto);
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
