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
import { JwtAuthGuard } from 'src/auth/jwt-auth.guard';
import { LocalAuthGuard } from 'src/auth/local-auth.guard';
import { SaveUserDto } from './dto/saveUser.dto';
import { UserService } from './user.service';

@Controller('user')
export class UserController {
  constructor(
    private readonly useService: UserService,
    private readonly authService: AuthService,
  ) {}

  @Post('/saveUser')
  async saveUser(@Body() saveUserDto: SaveUserDto) {
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
