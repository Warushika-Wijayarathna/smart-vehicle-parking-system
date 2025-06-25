export type PaymentStatus = 'PENDING' | 'SUCCESS' | 'FAILED';

export interface Payment {
    id?: number;
    user_id: string;
    amount: number;
    status?: PaymentStatus;
    transaction_time?: Date;
}
