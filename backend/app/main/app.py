from flask import Blueprint, request
from ..models.User import db, User
import os

main = Blueprint(
    'main',
    __name__,
    template_folder='templates/main',
    url_prefix='/'
)

def verify_pass(email, pw):
    user = User.query.filter(User.email == email).first().__dict__
    if (user["password"] == pw):
        return True
    return False

@main.route('/', methods=['GET'])
def index():
    return "Welcome to the Backend"

@main.route('/signup', methods=['POST'])
def signup():
    email = request.form.get("email").upper()
    password = request.form.get("password")
    phone = request.form.get("phone")
    public_key = "SAMPLE_PUBLIC_KEY"

    # Example of how data can be created initialized db
    data = User(email, password, phone, public_key)
    db.session.add(data)
    db.session.commit()
    
    return {"ok": 200}

@main.route('/signin', methods=['POST'])
def signin():
    print("Line  38")
    email = request.form.get("email").upper()
    user = User.query.filter(User.email == email).first()
    print("Line  41")
    if user:
        user = user.__dict__
        if verify_pass(email, request.form.get("password")):
            return {"message": "user found"}
    print("Line  46")
    return {"message": "user NOT found"}
