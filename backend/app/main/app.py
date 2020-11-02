import hashlib

from flask import Blueprint, request
from ..models.User import db, User

main = Blueprint(
    'main',
    __name__,
    template_folder='templates/main',
    url_prefix='/'
)

def create_user(email, password, phone, public_key):
    user_email = User.query.filter(User.email == email).first()
    user_phone = User.query.filter(User.phone == phone).first()

    if user_email or user_phone:
        print("USER NOT CREATED. PHONE OR EMAIL ALREADY EXISTS.")
        return
    
    password = hashlib.sha256(password.encode()).hexdigest()
    email = email.upper()
    data = User(email, password, phone, public_key)
    db.session.add(data)
    db.session.commit()

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
    # TO-DO: IMPLEMENT PROPER SIGNUP API
    email = request.form.get("email").upper()
    password = request.form.get("password")
    phone = request.form.get("phone")
    public_key = request.form.get("pk")

    create_user(email, password, phone, public_key)
    
    return {"ok": 200}

@main.route('/signin', methods=['POST'])
def signin():
    # TO-DO: IMPLEMENT PROPER SIGNIN API
    # Example of how data can be checked initialized db
    email = request.form.get("email").upper()
    user = User.query.filter(User.email == email).first()
    
    if user:
        user = user.__dict__
        if verify_pass(email, request.form.get("password")):
            return {"message": "user found"}

    return {"message": "user NOT found"}
