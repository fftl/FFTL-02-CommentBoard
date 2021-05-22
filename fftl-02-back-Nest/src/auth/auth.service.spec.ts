// import { JwtModule, JwtService } from '@nestjs/jwt';
// import { Test, TestingModule } from '@nestjs/testing';
// import { getRepositoryToken } from '@nestjs/typeorm';
// import { Repository } from 'typeorm';
// import { User } from '../entities/user.entity';
// import { UserService } from '../user/user.service';
// import { AuthService } from './auth.service';
// import { jwtConstants } from './constants';

// const mockRepository = () => ({
//   save: jest.fn(),
//   find: jest.fn(),
//   findOne: jest.fn(),
// })

// type MockRepository<T = any> = Partial<Record<keyof Repository<T>, jest.Mock>>;

// describe('AuthService', () => {
//   let authService: AuthService;
//   let userRepository: MockRepository<User>;

//   beforeEach(async () => {
//     const module: TestingModule = await Test.createTestingModule({
//       providers: [
//         AuthService, UserService,
//         {provide : getRepositoryToken(User), useValue:mockRepository() }
//       ],
//     }).compile();
//     userRepository = module.get(getRepositoryToken(User));
//     authService = module.get<AuthService>(AuthService);
//   });

//   const user = {uid:1, username:"user", password:"1234", nickname:"lee", boards: [], comments:[]};

//   it('should be defined', () => {
//     expect(authService).toBeDefined();
//   });

//   it('not found User test', async () => {
//     await userRepository.save(user);
//     const result = await authService.validateUser("user", "1234");

//     expect(userRepository.findOne).toHaveBeenCalledTimes(1);
//     expect(result).toBe(user);
//   })

// });