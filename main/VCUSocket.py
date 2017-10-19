import socket

class VCUSocket:

    """
    Creates a local socket to communicate with the VCU, mimicking the behaviour of the WirelessIno app. Possibly better
    response times than sending the data to the CAN-network using can-utils. Use the send(data) method to send a string
    to the VCU, preferably in the "VxxxxHxxxx" format.
    """

    def __init__(self):
        self.HOST = "localhost"
        self.PORT = 9000
        self.initiateConnection()

    def initiateConnection(self):
        self.clientsocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.clientsocket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.clientsocket.connect((self.HOST, self.PORT))

        print("VCUSocket connected")

    def send(self, data):
        if data:
            data.strip('\n')
            self.clientsocket.send(data.encode('utf-8'))

    def closeConnection(self):
        self.clientsocket.close()
        print("VCUSocket closed")