import socket
import re

class SensorDataCollector:

    def __init__(self):
        self.canSocket = initializeCAN("can0")
        self.sensorData = 0.0
        self.run()

    def run(self):
        part2 = b""
        while True:
            data = self.canSocket.recv(1024)
            if (data[0], data[1]) == (108, 4):
                # Reading DistPub this way is not a good idea, since those
                # messages come often and slow down the other threads (or the
                # whole process?).
                # DistPub
                # note that non-ASCII will appear as text \x07 in 'parts'
                if data[8] == 16:
                    if len(part2) > 18:
                        part2x = part2[19:]
                        part2s = part2x.decode('ascii')
                        l = part2[18]
                        part2s2 = part2s[0:l]
                        m = re.search("([0-9]+) ([0-9]+)", part2s2)
                        if m:
                            self.sensorData = (int(part2s2.split()[1]) / 100)
                        part2 = b""
                part2 += data[9:]

    def get(self):
        return self.sensorData

def initializeCAN(network):
    """
    Initializes the CAN network, and returns the resulting socket.
    """
    # create a raw socket and bind it to the given CAN interface
    s = socket.socket(socket.AF_CAN, socket.SOCK_RAW, socket.CAN_RAW)
    s.bind((network,))
    return s