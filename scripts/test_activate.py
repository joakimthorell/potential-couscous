
from python_server import PythonServer

def doSomething(str):
    print(str)

print('starting test')
p = PythonServer(doSomething(str))

p.start_server()


