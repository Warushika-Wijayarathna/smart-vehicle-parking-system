from bson.objectid import ObjectId
from flask import jsonify
import requests

from app import vehicles_col
from models.vehicle import vehicle_schema
from utils.response import error_response, success_response


def register_vehicle(data, auth_header):
    errors = vehicle_schema.validate(data)
    if errors:
        return error_response(errors, 400)

    user_id = data.get("user_id")
    print("Token:", auth_header)
    try:
        user_response = requests.get(f"http://localhost:8080/user-service/api/user/getByToken", headers={"Authorization": auth_header})
        if user_response.status_code == 404:
            return error_response("User not found", 404)
        elif user_response.status_code != 200:
            return error_response("Failed to verify user ID", 500)
    except requests.exceptions.RequestException as e:
        return error_response(f"Error verifying user ID: {str(e)}", 500)

    vehicle = {
        "vehicle_type": data.get("vehicle_type"),
        "user_id": user_response.json().get("id"),
        "vehi_reg_number": data.get("vehi_reg_number"),
        "color": data.get("color"),
        "year": data.get("year"),
    }
    result = vehicles_col.insert_one(vehicle)
    vehicle["_id"] = str(result.inserted_id)
    return success_response(vehicle, 201)

def get_vehicles_by_user(auth_header):
    try:
        user_response = requests.get(
            "http://localhost:8080/user-service/api/user/getByToken",
            headers={"Authorization": auth_header}
        )
        if user_response.status_code == 404:
            return error_response("User not found", 404)
        elif user_response.status_code != 200:
            return error_response("Failed to verify user", 500)

        authenticated_user_id = user_response.json().get("id")

        vehicles = list(vehicles_col.find({"user_id": authenticated_user_id}))
        for vehicle in vehicles:
            vehicle["_id"] = str(vehicle["_id"])
        return success_response(vehicles)

    except requests.exceptions.RequestException as e:
        return error_response(f"Error verifying user ID: {str(e)}", 500)

def update_vehicle(_id, data):
    errors = vehicle_schema.validate(data)
    if errors:
        return error_response(errors, 400)

    update_fields = {
        "vehicle_type": data.get("vehicle_type"),
        "user_id": data.get("user_id"),
        "vehi_reg_number": data.get("vehi_reg_number"),
        "color": data.get("color"),
        "year": data.get("year"),
    }
    result = vehicles_col.update_one({"_id": ObjectId(_id)}, {"$set": update_fields})
    if result.matched_count == 0:
        return error_response("Vehicle not found", 404)

    updated_vehicle = vehicles_col.find_one({"_id": ObjectId(_id)})
    updated_vehicle["_id"] = str(updated_vehicle["_id"])
    return success_response(updated_vehicle)


def delete_vehicle(_id):
    result = vehicles_col.delete_one({"_id": ObjectId(_id)})
    if result.deleted_count == 0:
        return error_response("Vehicle not found", 404)
    return success_response({"deleted": True})
