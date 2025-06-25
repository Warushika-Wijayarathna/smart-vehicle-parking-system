from flask import Blueprint, request
from services.vehicle_service import register_vehicle, get_vehicles_by_user, update_vehicle, delete_vehicle

vehicle_bp = Blueprint('vehicle', __name__)

# Create vehicle
@vehicle_bp.route('/vehicles', methods=['POST'])
def create_vehicle():
    auth_header = request.headers.get('Authorization')
    return register_vehicle(request.json, auth_header)

# ✅ Get vehicles for authenticated user
@vehicle_bp.route('/vehicles/user', methods=['GET'])
def get_by_user():
    auth_header = request.headers.get('Authorization')
    return get_vehicles_by_user(auth_header)

# Update vehicle (authenticated)
@vehicle_bp.route('/vehicles/<id>', methods=['PUT'])
def update(id):
    auth_header = request.headers.get('Authorization')
    return update_vehicle(id, request.json, auth_header)

# Delete vehicle (authenticated)
@vehicle_bp.route('/vehicles/<id>', methods=['DELETE'])
def delete(id):
    auth_header = request.headers.get('Authorization')
    return delete_vehicle(id, auth_header)
