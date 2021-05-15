import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AuthModule } from '../auth/auth.module';
import { Board } from '../entities/board.entity';
import { UserModule } from '../user/user.module';
import { BoardController } from './board.controller';
import { BoardService } from './board.service';

@Module({
  imports: [TypeOrmModule.forFeature([Board]), AuthModule, UserModule],
  controllers:[BoardController],
  providers: [BoardService],
  exports: [BoardService],
})
export class BoardModule {}
