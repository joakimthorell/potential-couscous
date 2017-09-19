import time
import datetime
from random import random
from scripts import SensorHandler

valueLog = "Sensor test data from " + datetime.datetime.fromtimestamp(time.time()).strftime('%Y-%m-%d %H:%M:%S') + "\n"
sh = SensorHandler.SensorHandler(3400)

for x in range(0, 20 * 5):
    num = random() + 4
    sh.put(num)
    valueLog += str(num) + " - " + str(sh.get()) + "\n"
    time.sleep(0.02)

print(valueLog)
#text_file = open("sensor-output.txt", "w")
#text_file.write(valueLog)
#text_file.close()
