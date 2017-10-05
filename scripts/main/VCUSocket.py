import socket

class VCUSocket:

    def __init__(self):
        self.HOST = "localhost"
        self.PORT = "9000"
        self.initiateConnection()

    def initiateConnection(self):
        self.clientsocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.clientsocket.connect((self.HOST, self.PORT))

    def send(self, data):
        self.clientsocket.send(data)