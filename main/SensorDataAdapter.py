from subprocess import Popen, PIPE, STDOUT
import threading

class SensorDataAdapter():

    """
    Starts a subprocess of SensorDataCollector and reads the output. Use the get() method to retrieve the latest sensor
    data.
    """

    def __init__(self):
        self.collector = Popen(['python3', 'main/SensorDataCollector.py'], stdout=PIPE, stderr=STDOUT)
        self.sensorData = 0.0
        threading.Thread(target=self.run, args=()).start()

    def run(self):
        while True:
            output = self.collector.stdout.readline()
            output = output.strip('\n')
            if output != '':
                self.sensorData = float(output)

    def get(self):
        return self.sensorData
