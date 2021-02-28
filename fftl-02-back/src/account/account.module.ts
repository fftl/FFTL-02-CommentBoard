import { Controller, Module } from '@nestjs/common';
import { TypeOrmModule } from '@nestjs/typeorm';
import { AccountController } from './account.controller';
import { AccountService } from './account.service';

@Module({
    imports: [],
    controllers :[AccountController],
    providers : [AccountService]
})
export class AccountModule {}
