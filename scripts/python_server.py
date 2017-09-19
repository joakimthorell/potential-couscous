'''
    Simple socket server using threads
'''

import socket
import sys
from thread import *

HOST = ''  # Symbolic name meaning all available interfaces
PORT = 8888  # Arbitrary non-privileged port

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print 'Socket created'

# Bind socket to local host and port
try:
    s.bind((HOST, PORT))
except socket.error as msg:
    print 'Bind failed. Error Code : ' + str(msg[0]) + ' Message ' + msg[1]
    sys.exit()

print 'Socket bind complete'

# Start listening on socket
s.listen(10)  # the number specifies nums of allowed failed connections before termination
print 'Socket now listening'


# Function for handling connections. This will be used to create threads
def clientthread(conn): # shadow-naming s becomes conn from here on.
    # Sending message to connected client
    print 'Someone connected to server...'

    # infinite loop so that function do not terminate and thread do not end.
    while True:

        # Receiving from client
        data = conn.recv(1024) # equals java read-func, nums specify maximum byte-size of what could be recv.
        print 'Server received ' + data
        if not data:
            break

    # conn.sendall(reply)

    # came out of loop
    conn.close()


# now keep talking with the client
while 1:
    # wait to accept a connection - blocking call (blocking call means it waits until data is transferd from conn).
    conn, addr = s.accept()
    print 'Connected with ' + addr[0] + ':' + str(addr[1])

    # start new thread takes 1st argument as a function name to be run, second is the tuple of arguments to the func.
    start_new_thread(clientthread, (conn,))

s.close()