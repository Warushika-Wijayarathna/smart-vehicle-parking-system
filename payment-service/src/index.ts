import express, { Request, Response } from "express";
import dotenv from "dotenv"
import {errorHandler} from "./middlwares/errorHandler";
import {connectDB} from "./db/db";
import rootRouter from "./routes";
import eurekaClient from "./eureka/eurekaClient";
import {AppDataSource} from "./db/data-source";


dotenv.config()
const app = express();

app.use(express.json())

const PORT = process.env.SERVER_PORT;
app.use("/api",rootRouter)
// Use errorHandler middleware
app.use(errorHandler);

// Start Eureka client
eurekaClient.start((error: any) => {
    if (error) {
        console.error("Failed to connect to Eureka server:", error);
    } else {
        console.log("Eureka client registered successfully!");
    }
});

connectDB().then(() => {
    app.listen(PORT, () => {
        console.log(`Server is running on http://localhost:${PORT}`);
    });
})

AppDataSource.initialize().then(() => {
    console.log("✅ Database connected!");
});
