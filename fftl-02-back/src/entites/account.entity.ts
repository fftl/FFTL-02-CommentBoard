import { Column, Entity, ManyToOne, PrimaryGeneratedColumn, Timestamp } from "typeorm";
import { User } from "./user.entity";

@Entity()
export class Account {
    @PrimaryGeneratedColumn()
    accountId: number;

    @Column()
    name : string;

    @Column()
    price : number;

    @Column()
    date : Date;

    @Column()
    time : Timestamp;

    // @ManyToOne()
    // userId : number;

}