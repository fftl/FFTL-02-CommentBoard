import { Board } from "src/entities/board.entity";
import { Comment } from "src/entities/comments.entity";

export class SaveUserDto{
    readonly uid : number;
    readonly username : string;
    readonly password : string;
    readonly nickname : string;
    readonly boards : Board[];
    readonly comments : Comment[];
}