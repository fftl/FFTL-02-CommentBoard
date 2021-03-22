import { type } from "node:os";
import { Timestamp } from "rxjs";
import { Category } from "src/category/category.entity";
import { Column, Entity, OneToOne, PrimaryGeneratedColumn } from "typeorm";
import { ForeignKeyMetadata } from "typeorm/metadata/ForeignKeyMetadata";

@Entity()
export class Account {
    @PrimaryGeneratedColumn()
    accountId: number

    @OneToOne(
        (type) => Category
    )
    categoryId : Category

    @Column()
    actName: string

    @Column()
    price: number

    @Column()
    actDate: Date

    @Column()
    actTime: Date
}