import hashlib
import json

from flask import Blueprint, request, Response
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
    user_pk = User.query.filter(User.public_key == public_key).first()

    if user_email or user_phone or user_pk:
        print("USER NOT CREATED. PHONE, EMAIL OR PK ALREADY EXISTS.")
        return False
    
    password = hashlib.sha256(password.encode()).hexdigest()
    email = email.upper()
    data = User(email, password, phone, public_key)
    db.session.add(data)
    db.session.commit()
    return data

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
    email = request.form.get("email").upper().strip()
    password = request.form.get("password")
    phone = request.form.get("phone").strip()
    public_key = request.form.get("pk").strip()
    
    if not email or not password or not phone or not public_key:
        return Response('{"message": "missing required fields"}', status=400, mimetype='application/json')

    user = create_user(email, password, phone, public_key)
    
    user = {
        "email": user.email,
        "phone": user.phone,
        "pk": user.public_key
    }

    if not user:
        return Response('{"message": "phone number, email or pk already exists"}', status=400, mimetype='application/json')
    
    return Response(json.dumps(user), status=200, mimetype='application/json')

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
