import { Entity, Column, PrimaryGeneratedColumn } from 'typeorm';

@Entity()
export class category {
  @PrimaryGeneratedColumn()
  categoryId: number;

  @Column()
  categoryName: string;
}