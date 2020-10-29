from flask import Flask
from config import config

import os
from os.path import join, dirname
from dotenv import load_dotenv
from .models.User import db

# config module is available here
# https://github.com/miguelgrinberg/flasky/issues/154
# By default, the current directory for a process is the directory
# from where the application was started.

# Create config file path.
config_file_path = join(dirname(__file__), '../instance/config.py')

# Load file from the path.
load_dotenv(config_file_path)

def create_app(config_name, initdb):
    app = Flask(__name__, instance_relative_config=True)
    app.config.from_object(config[config_name])
    config[config_name].init_app(app)

    app.config['SQLALCHEMY_DATABASE_URI'] = os.getenv("DATABASE_URL") if os.getenv('ENV') == "development" else os.getenv("PROD_DATABASE_URL")
    app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False 
    db.init_app(app)

    if initdb:
        with app.app_context():
            db.create_all()

    # This is the main Blueprint
    from .main.app import main as main_blueprint
    app.register_blueprint(main_blueprint)
    # Register other Blueprints below ...

    return app
