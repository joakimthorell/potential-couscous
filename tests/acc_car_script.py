import nav as n
from nav import *
from nav1 import whole4, pause, cont
from driving import stop, drive, steer
init()

import time
import SensorHandler

minDist = 0.6
currentDist = 0.0
goalVelocity = 10
currentSpeed = goalVelocity
k1 = 4
k2 = 0.1
k3 = 1

i = 0

timeInterval = 0.1

totIntegral = 0.0
lastErr = 0.0

sh = SensorHandler.SensorHandler(34.00, 0.7)

def p (_k1, _curDist, _minDist):
        print ("p: " + str(k1 * (_curDist - _minDist)))
        return _k1 * (_curDist - _minDist)

def integral (_k2, _lastIntegral, _curDist, _minDist, _timeInterval):
        _newIntegral = _lastIntegral + (_curDist - _minDist) * _timeInterval
        totIntegral = k2 * _newIntegral
        print ("I: " +str(k2 * _newIntegral))
        return k2 * _newIntegral

def deriv (_k3, _currentDist, _lastErr, _minDist, _timeInterval) :
        _currentErr = _currentDist - _minDist
        lastErr = _currentErr
        deriv = _k3 * (_currentErr - _lastErr) / _timeInterval
        print ("d: " +str(deriv))
        return deriv


time.sleep(1)
g.limitspeed=None
braking = False
while True:

    sh.put(g.can_ultra)
    # currentDist = sh.get()
    currentDist = g.can_ultra

    print ("Current dist: " +str(currentDist))

    if currentDist < 2.0:
        output = p(k1, currentDist, minDist) + integral(k2, totIntegral, currentDist, minDist, timeInterval) + deriv(k3, currentDist, lastErr, minDist, timeInterval)

        print ("Output " + str(output))
        print ("Speed " + str(currentSpeed))
        print ("Dist " + str(currentDist))
        print ("Diff " + str(lastErr))
        print ("")

        speed = currentSpeed + output

        if speed > goalVelocity:
            if speed < currentSpeed:
                drive(-100)
                time.sleep(0.2)
                drive(0)
                time.sleep(0.2)
            currentSpeed = goalVelocity
            braking = False
        elif speed <= 0:
            if not braking:
                drive(-100)
                time.sleep(2)
                braking = True

            speed = 0
        else:
            currentSpeed = speed
            braking = False

        drive(int(currentSpeed))

    else:
        drive(goalVelocity)
        totIntegral = 0

    time.sleep(timeInterval)