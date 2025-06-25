from flask import Blueprint, request
from services.vehicle_service import register_vehicle, get_vehicles_by_user, update_vehicle, delete_vehicle

vehicle_bp = Blueprint('vehicle', __name__)

# Create vehicle
@vehicle_bp.route('/vehicles', methods=['POST'])
def create_vehicle():
    return register_vehicle(request.json)

@vehicle_bp.route('/vehicles/user/<user_id>', methods=['GET'])
def get_by_user(user_id):
    return get_vehicles_by_user(user_id)

@vehicle_bp.route('/vehicles/<id>', methods=['PUT'])
def update(id):
    return update_vehicle(id, request.json)

@vehicle_bp.route('/vehicles/<id>', methods=['DELETE'])
def delete(id):
    return delete_vehicle(id)
