from app import create_app

application = create_app('production', True)

# To Be used by a WSGI Server like Gunicorn
