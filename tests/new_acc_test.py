import nav as n
from nav import *
from nav1 import whole4, pause, cont
from driving import stop, drive, steer
init()

import time
import SensorHandler

currentDist = 0.0
lastDist = 0.7
goalVelocity = 20
currentSpeed = goalVelocity
timeInterval = 0.2
sh = SensorHandler.SensorHandler(34.00, 0.7)

time.sleep(1)
g.limitspeed=None

while True:
    sh.put(g.can_ultra)
    currentDist = sh.get()

    if currentDist < 0.7:
        if (currentDist / lastDist) > 1.1:
            drive(int(currentSpeed/2))

    else:
        currentSpeed = goalVelocity
        drive(currentSpeed)
    print ("Speed: " + str(currentSpeed))

    time.sleep(timeInterval)
