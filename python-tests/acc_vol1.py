import time

minDist = 0.5
currentDist = 1.0
goalVelocity = 30
currentSpeed = goalVelocity
k1 = 0.5
k2 = 0.1
k3 = 1

timeInterval = 0.01

totIntegral = 0.0
lastErr = 0.0

def p ( _curDist ):
	return k1 * (_curDist - minDist)

def integral (_lastIntegralSum, _curDist): 
	totIntegral = _lastIntegralSum + (_curDist - minDist)/timeInterval
	return k2 * totIntegral

def deriv (_currentDist, _lastErr) :
	_currentErr = _currentDist - minDist
	currentErr = _currentErr
	return k3 * (_currentErr - _lastErr) / timeInterval

while True:
    output = p(currentDist) + integral(totIntegral, currentDist) + deriv(currentDist, lastErr)

    lastErr = currentDist - minDist

    totIntegral = totIntegral + (currentDist - minDist)/timeInterval

    speed = output

    if speed > goalVelocity:
        currentSpeed = goalVelocity
    else:
        currentSpeed = speed

    print ("Output " + str(output))
    print ("Speed " + str(currentSpeed))
    print ("Dist " + str(currentDist))

    currentDist = currentDist - 0.001 * currentSpeed

    time.sleep(timeInterval)

