from flask import Flask
from flask_pymongo import PyMongo
from flask_cors import CORS
from dotenv import load_dotenv
import os
from config.config import Config

# Load env vars
load_dotenv()

# App setup
app = Flask(__name__)
CORS(app)

app.config["MONGO_URI"] = Config.MONGO_URI
mongo = PyMongo(app)
vehicles_col = mongo.db.vehicles

# Register routes
from routes.vehicle_routes import vehicle_bp
app.register_blueprint(vehicle_bp)
