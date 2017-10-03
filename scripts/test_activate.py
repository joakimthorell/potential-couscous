
from python_server import start_server

def doSomething(str):
    print(str)

print('starting test')

start_server(doSomething(str))

while True:
    x = 'a'



