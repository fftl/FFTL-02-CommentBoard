import { Column, Entity, PrimaryGeneratedColumn } from "typeorm";

@Entity()
export class Users {
    @PrimaryGeneratedColumn()
    userId: number

    @Column()
    userName: string

    @Column()
    passWord: string

    @Column()
    nickName: string
}