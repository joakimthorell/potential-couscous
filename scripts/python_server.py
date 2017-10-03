'''
    Server class that sends the data to its listeners
'''

import socket
from _thread import *

HOST = '' #this will be device ip
PORT = 8888

def clientthread(conn, listener):  # shadow-naming s becomes conn from here on.
    # Sending message to connected client
    print('Someone connected to server...')

    # infinite loop so that function do not terminate and thread do not end.
    while True:

        # Receiving from client
        byteData = conn.recv(1024)  # equals java read-func, nums specify maximum byte-size of what could be recv.
        # print(('Server received ' + byteData))

        data = byteData.decode('utf-8')
        data = str(data)

        listener(data)

    # came out of loop
    conn.close()

def listen_for_connections(socket, listener):
    while 1:
        # wait to accept a connection - blocking call (blocking call means it waits until data is transferd from conn).
        conn, addr = socket.accept()
        print(('Connected with ' + addr[0] + ':' + str(addr[1])))

        # start new thread takes 1st argument as a function name to be run, second is the tuple of arguments to the fun
        start_new_thread(clientthread, (conn, listener))


def start_server(listener):
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    print('Socket created')
    # Bind socket to local host and port
    try:
        s.bind((HOST, PORT))
    except socket.error as msg:
        print(('Bind failed. Error Code : ' + str(msg[0]) + ' Message ' + msg[1]))
    print('Socket bind complete')

    # Start listening on socket
    s.listen(10)  # the number specifies nums of allowed failed connections before termination
    print('Socket now listening')
    start_new_thread(listen_for_connections, (s, listener))
