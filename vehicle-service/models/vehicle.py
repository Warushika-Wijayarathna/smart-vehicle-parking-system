# vehicle-service/models/vehicle.py
from marshmallow import Schema, fields, validate

class VehicleSchema(Schema):
    vehicle_type = fields.String(required=True)
    user_id = fields.String(required=True)
    vehi_reg_number = fields.String(required=True, validate=validate.Length(max=20))
    color = fields.String(required=False)
    year = fields.Integer(required=False)

vehicle_schema = VehicleSchema()