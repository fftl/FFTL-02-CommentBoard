import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm";
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

    @OneToMany(
        (type) => Board,
        (boards) => boards.user,
    )
    boards: Board[];

    @OneToMany(
        (type) => Comment,
        (comments) => comments.user,
    )
    comments: Comment[];
}