import { Test, TestingModule } from '@nestjs/testing';
import { getRepositoryToken } from '@nestjs/typeorm';
import { User } from 'src/entities/user.entity';
import { UserService } from './user.service';

class MockRepository{
  async findOne(username: string) {
    const user : User = new User();
    user.username = username;
    return user;
  };
};

describe('UserService', () => {
  let service: UserService;

  beforeEach(async () => {
    const module: TestingModule = await Test.createTestingModule({
      providers: [
        UserService,
        {
          provide: getRepositoryToken(User),
          useClass: MockRepository,
        },
      ],
    }).compile();

    service = module.get<UserService>(UserService);
  });

  it('should be defined', () => {
    expect(service).toBeDefined();
  });

  it("입력된 아이디에 해당하는 유저의 정보를 반환해야 합니다.", async () =>{
    const username = "user";
    const myUser = await service.findOne(username);
    expect(myUser.username).toEqual(username);
  });
});
