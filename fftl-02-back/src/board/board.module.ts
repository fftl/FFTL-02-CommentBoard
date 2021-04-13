import { Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AuthModule } from 'src/auth/auth.module';
import { Board } from 'src/entities/board.entity';
import { BoardController } from './board.controller';
import { BoardService } from './board.service';

@Module({
  imports: [TypeOrmModule.forFeature([Board]), AuthModule],
  controllers:[BoardController],
  providers: [BoardService],
  exports: [BoardService],
})
export class BoardModule {}
