from flask import render_template, Blueprint, send_from_directory
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
    return None

@main.route('/signin', methods=['POST'])
def signin():
    return None
