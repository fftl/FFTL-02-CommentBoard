import { Column, Entity, JoinColumn, ManyToOne, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { Comment } from "./comment.entity";
import { User } from "./user.entity";

@Entity()
export class Board {
    @PrimaryGeneratedColumn()
    bid: number;

    @Column()
    title: string;

    @Column()
    content: string;

    @Column()
    nickname: string;

    @Column()
    bregdate: string;

    @ManyToOne(
        (type) => User,
        (user) => user.boards,
    )
    user : User;


    @OneToMany(
        (type) => Comment,
        (comments) => comments.board,
    )
    comments: Comment[];

    constructor(title: string, content: string, nickname: string, bregdate: string, user: User){
        this.title = title;
        this.content = content;
        this.nickname = nickname;
        this.bregdate = bregdate;
        this.user = user;
    }
}