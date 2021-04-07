import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";
import { Board } from "./board.entity";
import { Comment } from "./comments.entity";

@Entity()
export class User {
    @PrimaryGeneratedColumn()
    uid: number;

    @Column()
    username: string;

    @Column()
    password: string;

    @Column()
    nickname: string;

    @Column()
    boards: Board[];

    @Column()
    comments: Comment[];
}