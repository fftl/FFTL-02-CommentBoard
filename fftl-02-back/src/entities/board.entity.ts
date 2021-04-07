import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";
import { Comment } from "./comments.entity";

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
    uid: number;

    @Column()
    bregdate: string;

    @Column()
    comments: Comment[];

}