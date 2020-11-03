from flask_sqlalchemy import SQLAlchemy

db = SQLAlchemy()

class User(db.Model):
  __tablename__ = 'user'
  id = db.Column(db.Integer, primary_key=True, unique=True, autoincrement=True, nullable=False)
  email = db.Column(db.VARCHAR(100), unique=True, nullable=False)
  phone = db.Column(db.CHAR(11), unique=True, nullable=False)
  password = db.Column(db.Text(), nullable=False)
  public_key = db.Column(db.Text(), unique=True, nullable=False)

  def __init__(self, email, password, phone, public_key):
    self.email = email
    self.password = password
    self.phone = phone
    self.public_key = public_key