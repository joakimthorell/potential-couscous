import time

minDist = 0.5
currentDist = 1.0
goalVelocity = 7
currentSpeed = goalVelocity
k1 = 2
k2 = 2
k3 = 1

i = 0

hostVelocity = 0

timeInterval = 0.01

totIntegral = 0.0
lastErr = 0.0

def p (_k1, _curDist, _minDist):
	return _k1 * (_curDist - _minDist)

def integral (_k2, _lastIntegral, _curDist, _minDist, _timeInterval): 
	_newIntegral = _lastIntegral + (_curDist - _minDist) * _timeInterval
	return k2 * totIntegral

def deriv (_k3, _currentDist, _lastErr, _minDist, _timeInterval) :
	_currentErr = _currentDist - _minDist
	_lastErr = _lastErr
	return _k3 * (_currentErr - _lastErr) / _timeInterval

while True:
    output = p(k1, currentDist, minDist) + integral(k2, totIntegral, currentDist, minDist, timeInterval) + deriv(k3, currentDist, lastErr, minDist, timeInterval)

    lastErr = currentDist - minDist

    totIntegral = totIntegral + (currentDist - minDist) * timeInterval

    speed = currentSpeed + output

    if speed > goalVelocity:
        currentSpeed = goalVelocity
    elif speed < 0:
        currentSpeed = 0
    else:
        currentSpeed = speed

    print ("Output " + str(output))
    print ("Speed " + str(currentSpeed))
    print ("Dist " + str(currentDist))
    print ("Diff " +  str(lastErr))
    print ("")

    # i+= 1
    # if i % 100 == 0:
    #     hostVelocity += 0.1

    currentDist = currentDist - (currentSpeed - hostVelocity)*timeInterval

    time.sleep(timeInterval)