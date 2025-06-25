import { Entity, PrimaryGeneratedColumn, Column, CreateDateColumn } from "typeorm";

export type PaymentStatus = "PENDING" | "SUCCESS" | "FAILED";

@Entity("payments")
export class PaymentEntity {
    @PrimaryGeneratedColumn()
    id!: number;

    @Column()
    user_id!: string;

    @Column("decimal", { precision: 10, scale: 2 })
    amount!: number;

    @Column({ type: "enum", enum: ["PENDING", "SUCCESS", "FAILED"], default: "PENDING" })
    status!: PaymentStatus;

    @CreateDateColumn()
    transaction_time!: Date;
}
