import { Column, Entity, OneToMany, PrimaryGeneratedColumn } from "typeorm";
import { Account } from "./account.entity";

@Entity()
export class User{
    @PrimaryGeneratedColumn()
    userId:number;

    @Column()
    userName:string;

    @Column()
    passWord:string;

    @Column()
    nickName:string;

    // @OneToMany()
    // accounts:Account;

}