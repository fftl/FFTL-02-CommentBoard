import { Body, Controller, Post, UseGuards } from '@nestjs/common';
import { AuthGuard } from '@nestjs/passport';
import { SaveUserDto } from './dto/saveUser.dto';
import { UserService } from './user.service';

@Controller('user')
export class UserController {

    constructor(private readonly useService: UserService){}

    @Post("/saveUser")
    async saveUser(@Body() saveUserDto: SaveUserDto){
        return this.useService.saveUser(saveUserDto);
    }

    @UseGuards(AuthGuard('local'))
    @Post('/login')
    async login(@Request() req){
        return req.user;
    }
}
