import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity()
export class Comment{
    @PrimaryGeneratedColumn()
    cid: number;

    @Column()
    uid: number;

    @Column()
    bid: number;

    @Column()
    nickname: string;

    @Column()
    comment: string;

    @Column()
    cregdate: string; 
}