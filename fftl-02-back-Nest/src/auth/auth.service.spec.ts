import { JwtModule, JwtService } from '@nestjs/jwt';
import { Test, TestingModule } from '@nestjs/testing';
import { getRepositoryToken } from '@nestjs/typeorm';
import { User } from '../entities/user.entity';
import { getRepository } from 'typeorm';
import { UserService } from '../user/user.service';
import { AuthService } from './auth.service';
import { jwtConstants } from './constants';

const mockRepository = () => ({
  saveUser: jest.fn(),
  findOne: jest.fn(),
  findOneByUid: jest.fn(),
})

describe('AuthService', () => {
  let authService: AuthService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      imports:[
        JwtModule.register({
          secret: jwtConstants.secret,
          signOptions: { expiresIn: '3600s' },
        })
      ],
      providers: [
        AuthService, UserService,
        {provide : getRepositoryToken(User), useValue:mockRepository() }
    ],
    }).compile();

    authService = module.get<AuthService>(AuthService);
  });

  it('should be defined', () => {
    expect(authService).toBeDefined();
  });

});
