import os
from dotenv import load_dotenv

load_dotenv()

class Config:
    MONGO_URI = os.getenv("MONGO_URI", "mongodb+srv://ashani:FbmdQQcRxAUbTbNq@mini-pos.ubypzkx.mongodb.net/mini-pos-db")
    EUREKA_SERVER = os.getenv("EUREKA_SERVER", "http://localhost:8761/eureka")
    SERVICE_NAME = os.getenv("SERVICE_NAME", "vehicle-service")
    SERVICE_PORT = int(os.getenv("SERVICE_PORT", "5001"))
    SERVICE_HOST = os.getenv("SERVICE_HOST", "localhost")


