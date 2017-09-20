import time

minDist = 0.5
currentDist = 1.0
goalVelocity = 30
currentSpeed = goalVelocity
k1 = 1
k2 = 1
k3 = 1

hostVelocity = 20;

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
    output = p(currentDist) + integral(totIntegral, currentDist) + deriv(currentDist, lastErr)

    lastErr = currentDist - minDist

    totIntegral = totIntegral + (currentDist - minDist) * timeInterval

    speed = currentSpeed + output

    if speed > goalVelocity:
        currentSpeed = goalVelocity
    elif speed < 0:
        currentSpeed = 0;
    else:
        currentSpeed = speed

    print ("Output " + str(output))
    print ("Speed " + str(currentSpeed))
    print ("Dist " + str(currentDist))
    print ("Diff " +  str(lastErr))
    print ("")

    currentDist = currentDist - (currentSpeed - hostVelocity)*timeInterval

    time.sleep(timeInterval)