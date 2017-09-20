import time

minDist = 0.5
currentDist = 1.0
goalVelocity = 30
currentSpeed = goalVelocity
k1 = 0.5
k2 = 0.1
k3 = 0.3

timeInterval = 0.01

integral = 0.0
lastDiff = 0.0

while True:
    output = k1 * (currentDist - minDist) + k2 * (integral + (currentDist - minDist)/timeInterval) + k3 * ((currentDist - minDist) - lastDiff) / timeInterval

    lastDiff = currentDist - minDist

    integral = integral + (currentDist - minDist)/timeInterval

    speed = currentSpeed + output

    if speed > goalVelocity:
        currentSpeed = goalVelocity
    else:
        currentSpeed = speed

    print currentSpeed

    currentDist = currentDist - 0.001 * currentSpeed

    time.sleep(timeInterval)
