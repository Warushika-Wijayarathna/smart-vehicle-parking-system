from app import app
import py_eureka_client.eureka_client as eureka_client
from config.config import Config

def register_with_eureka():
    """Register this service with Eureka Server"""
    eureka_client.init(
        eureka_server=Config.EUREKA_SERVER,
        app_name=Config.SERVICE_NAME,
        instance_port=Config.SERVICE_PORT,
        instance_host=Config.SERVICE_HOST
    )
    print(f"Registered {Config.SERVICE_NAME} with Eureka server at {Config.EUREKA_SERVER}")

if __name__ == '__main__':
    register_with_eureka()
    app.run(host=Config.SERVICE_HOST, port=Config.SERVICE_PORT, debug=True)
