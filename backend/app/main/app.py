from flask import render_template, Blueprint, send_from_directory
from ..models.User import db, User
import os

main = Blueprint(
    'main',
    __name__,
    template_folder='templates/main',
    url_prefix='/'
)

@main.route('/', methods=['GET'])
def index():
    return "Welcome to the Backend"

@main.route('/signup', methods=['POST'])
def signup():
    # Example of how data can be created initialized db
    # data = User("test@email.com", "password", "1234235465", "lhauiaehhhluhs7832yhek")
    # db.session.add(data)
    # db.session.commit()
    return {"ok": 200}

@main.route('/signin', methods=['POST'])
def signin():
    return None
