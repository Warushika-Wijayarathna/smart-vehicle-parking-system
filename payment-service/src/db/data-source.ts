import { DataSource } from "typeorm";
import { PaymentEntity } from "../models/PaymentEntity";

export const AppDataSource = new DataSource({
    type: "mysql",
    host: process.env.DB_HOST,
    port: 3306,
    username: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    database: process.env.DB_NAME,
    synchronize: true, // Auto-create tables based on entities
    logging: true,
    entities: [PaymentEntity],
});

AppDataSource.initialize()
    .then(() => {
        console.log("✅ Database connected and tables created!");
    })
    .catch((error) => {
        console.error("❌ Database connection error:", error);
    });
