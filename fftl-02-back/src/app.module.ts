import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { AccountModule } from './account/account.module';
import { AuthModule } from './auth/auth.module';
import { CategoryModule } from './category/category.module';
import { UsersModule } from './users/users.module';
import { TypeOrmModule } from '@nestjs/typeorm';

@Module({
  imports: [AccountModule, AuthModule, CategoryModule, UsersModule],
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
