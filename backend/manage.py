import click
from app import create_app

@click.group()
def cli():
    pass

@click.command()
@click.option(
    '--env', default='development',
    help='Environment to use while running server',
    type=click.STRING
)
def initdb(env):
    app = create_app(env, True)

@click.command()
@click.option(
    '--env', default='development',
    help='Environment to use while running server',
    type=click.STRING
)
@click.option(
    '--port', default=5000,
    help='Port to use while running server',
    type=click.STRING
)
def runserver(env, port):
    app = create_app(env, False)
    app.run(port=port)

cli.add_command(cmd=runserver, name="runserver")
cli.add_command(cmd=initdb, name="initdb")

if __name__ == "__main__":
    cli()
