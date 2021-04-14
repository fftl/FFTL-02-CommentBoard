import { Column, Entity, ManyToMany, ManyToOne, PrimaryGeneratedColumn } from "typeorm";
import { Board } from "./board.entity";
import { User } from "./user.entity";

@Entity()
export class Comment{
    @PrimaryGeneratedColumn()
    cid: number;

    @Column()
    nickname: string;

    @Column()
    comment: string;

    @Column()
    cregdate: string; 

    @ManyToOne(
        (type) => User,
        (user) => user.comments,
    )
    user: User;

    @ManyToOne(
        (type) => Board,
        (board) => board.comments,
    )
    board: Board;
}