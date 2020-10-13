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
    return render_template('main/index.html.jinja')

@main.route('/signup', methods=['GET'])
def signup():
    return render_template('main/signup.html.jinja')

@main.route('/signin', methods=['GET'])
def signin():
    return render_template('main/signin.html.jinja')
