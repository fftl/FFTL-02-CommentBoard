import { Injectable, UnauthorizedException } from "@nestjs/common";
import { ExtractJwt } from "passport-jwt";
import { AuthService, jwtConstants } from "./auth.service";
import { JwtPayload } from "./jwt.payload";

@Injectable()
export class JwtStrategy extends PassportStrategy(Strategy) {
  constructor(private readonly authService: AuthService) {
    super({
      jwtFromRequest: ExtractJwt.fromAuthHeaderAsBearerToken(),
      ignoreExpiration: false,
      secretOrKey: jwtConstants.secret,
    });
  }

  // controller에 @UseGuards(JwtAuthGuard) 붙은 메서드들은 여기를 사전에 거쳐서 검증을 한다.
  async validate(payload: JwtPayload) {
    const user = await this.authService.validateAccount(payload);
    if (!user)
      throw new UnauthorizedException();
    return user;
  }
}

//auth.module.ts
@Module({
  imports: [
    AccountsService,
    PassportModule,
    JwtModule.register({
      secret: jwtConstants.secret,
      signOptions: {expiresIn: '60s'},
    }),
  ],
  providers: [AuthService, JwtStrategy],
  exports: [AuthService],
})
export class AuthModule {}

//
@Injectable()
export class JwtAuthGuard extends AuthGuard('jwt') {}