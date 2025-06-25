import { Router } from "express";
import PaymentController from "../controllers/payment.controller";

const paymentRouter = Router();

// Route to create a payment
paymentRouter.post("/", PaymentController.createPayment);

// Route to get payment details
paymentRouter.get("/:id", PaymentController.getPaymentDetails);

export default paymentRouter;
