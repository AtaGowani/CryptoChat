# Backend Component

## System Requirements

* Running Linux, MacOS, or Windows
* Must have Python 3.3 or greater
* PostgreSQL 13 w/ pgAdmin recommended ([download](https://www.postgresql.org/download/))

## Getting Started

### Setting up Repository

First clone the repostiory

```
$ git clone https://github.com/AtaGowani/secure_messaging_app.git
```
Change directory to backend

```
$ cd secure_messaging_app/backend
```
Create a virtual environment and install dependencies
```
$ python3 -m venv .venv
$ source .venv/bin/activate
$ python -m pip install --upgrade pip setuptools
$ python -m pip install -r requirements.txt
```

### Setting up the Database

**Must already have the db requirements met. See System Requirements section above**

Navigate into `backend/instance/` and create a new file named `config.py`. Copy the sample configs from `example_config.py` into this new file and update the `DATABASE_URL` with the appropriate local db information.

#### Initialize your Local Database

The command below will create a `User` schema in your connected db as defined in `app/models/User.py`.

```
$ python manage.py initdb
```

### Starting the Server

Start the development server
```
$ python manage.py runserver
```

Navigate to `http://127.0.0.1:5000/`

## Project layout

The **wsgi.py** file uses `production` as the environment. This file is to be used by WSGI servers such as Gunicorn.

You can also test production environment using `manage.py`

```
$ python manage.py runserver --env production
```

## manage.py

As mentioned earlier, **manage.py** has just one command **runserver** which is used to run the Flask development server.

It takes two optional parameters

- `env` - Environment to use while running server. Supports  `production`, `development` and `testing` out of the box. Default is `development`. 

- `port` - Port to use while running server. Default is 5000.

Eg.

```
$ python manage.py runserver --env production --port 5566
``` 

Runs the  server in `production` environment on port 5566.

You can  extend `manage.py` to support additional commands, parameters etc.
