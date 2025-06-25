import { Eureka } from "eureka-js-client";
import dotenv from "dotenv";

dotenv.config();

const eurekaClient = new Eureka({
    instance: {
        app: "smps-payment-service",
        instanceId: `${process.env.HOSTNAME}:${process.env.SERVER_PORT}`,
        hostName: process.env.HOSTNAME || "localhost",
        ipAddr: process.env.HOSTNAME || "127.0.0.1",
        port: {
            $: parseInt(process.env.SERVER_PORT || "5003"),
            "@enabled": true,
        },
        vipAddress: "smps-payment-service",
        statusPageUrl: `http://${process.env.HOSTNAME || "localhost"}:${process.env.SERVER_PORT}/status`,
        healthCheckUrl: `http://${process.env.HOSTNAME || "localhost"}:${process.env.SERVER_PORT}/health`,
        dataCenterInfo: {
            "@class": "com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo",
            name: "MyOwn",
        },
    },
    eureka: {
        host: process.env.EUREKA_HOST || "localhost",
        port: parseInt(process.env.EUREKA_PORT || "8761"),
        servicePath: "/eureka/apps/",
    },
});

export default eurekaClient;
