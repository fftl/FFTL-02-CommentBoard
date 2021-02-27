import { forwardRef, Inject, Injectable } from "@nestjs/common";
import { JwtService } from "@nestjs/jwt";
import { User } from "src/entites/user.entity";
import { UserService } from "src/user/user.service";
import { JwtPayload } from "./jwt.payload";

@Injectable()
export class AuthService {

    constructor(
        @Inject(forwardRef(()=> UserService))
        private readonly userService : UserService,
        private readonly jwtService : JwtService,
    ){}

    async validateAccount(payload: JwtPayload): Promise<any> {

        console.log(`validate account ${payload}`);
        console.log(`payload id : ${payload.userId}`);

        const user: User = await this.userService.findById(payload.userId)

        if(user.userName == payload.userName){
            console.log(`validateAccount Success`);
            const{...result} = user;
            return result
        }

        // Account를 받으면 JWT토큰을 새로 발급한다.
        return null;
    }

    makeAccessToken(user : User) {
        return this.jwtService.sign({'userName': user.userName});
    };
}

export const jwtConstants = {
    secret: 'secretKey'
}