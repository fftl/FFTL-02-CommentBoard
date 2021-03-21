import { forwardRef, Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AuthModule } from 'src/auth/auth.module';
import { Users } from './users.entity';
import { UsersService } from './users.service';

@Module({
  imports: [TypeOrmModule.forFeature([Users]), forwardRef(()=>AuthModule)],
  providers: [UsersService],
  exports: [UsersService],
})
export class UsersModule {}