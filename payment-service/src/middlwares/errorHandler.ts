import { NextFunction, Request, Response } from "express";
import { ApiError } from "../errors/ApiError";

export const errorHandler = (
    error: any,
    req: Request,
    res: Response,
    next: NextFunction
): void => {
    if (error instanceof ApiError) {
        res.status(error.statusCode).json({ message: error.message });
    } else {
        res.status(500).json({ message: "Internal server error" });
    }
};
