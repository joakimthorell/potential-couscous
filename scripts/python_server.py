'''
    Server class that sends the data to its listeners
'''

import socket
import sys
from _thread import *

class PythonServer:

    HOST = '' #this will be device ip
    PORT = 8888

    def __init__(self, listener):
        self.listener = listener
        self.isRunning = False
        self.serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    def look_for_clients(self):
        server = self.serverSocket
        while self.isRunning:
            conn, addr = server.accept()
            print('Someone connected to the server. ' + addr[0])
            start_new_thread(self._client_thread, self, conn)

        print('Closing server...')
        server.close()

    def start_server(self):
        try:
            serverSocket = self.serverSocket
            serverSocket.bind((self.HOST, self.PORT))
            serverSocket.listen(10)
            self.isRunning = True
            start_new_thread(self.look_for_clients, (self,))
        except socket.error as msg:
            print('Failed to start Socket')

        print('Socket open')

    @classmethod
    def _client_thread(self, conn):  # shadow-naming s becomes conn from here on.
        # Sending message to connected client
        print('Someone connected to server...')

        # infinite loop so that function do not terminate and thread do not end.
        while self.isRunning:

            # Receiving from client
            byteData = conn.recv(1024)  # equals java read-func, nums specify maximum byte-size of what could be recv.
            # print(('Server received ' + byteData))

            data = byteData.decode('utf-8')

            self.listener(data)

        # came out of loop
        print('Closing server...')
        conn.close()

    @classmethod
    def close_server(self):
        print('Closing server...')
        self.isRunning = False
        PythonServer.isRunning = False
