from app import create_app

application = create_app('production', False)

# To Be used by a WSGI Server like Gunicorn
