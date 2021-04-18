import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { Board } from "./board.entity";
import { Comment } from "./comment.entity";

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

    @OneToMany(
        (type) => Board,
        (board) => board.user,
    )
    boards: Board[];

    @OneToMany(
        (type) => Comment,
        (comment) => comment.user,
    )
    comments: Comment[];
}