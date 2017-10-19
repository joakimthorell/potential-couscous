#!/usr/bin/python
# -*- coding: utf-8 -*-
from collections import deque
from DrivingMode import DrivingMode


class ACCDrivingMode(DrivingMode):

    def __init__(self):

        #Tweaking variables
        self.y = 0.40
        self.errormargin = 0.05
        self.derivateErrormargin = 0.08
        self.derivateEmergencymargin = -0.3
        self.secondDerivateErrormargin = 0.08
        self.minSpeed = 13

        #Regular variables
        self.derivateList = deque([[0, 0], [0, 0], [0, 0], [0, 0], [0, 0]])
        self.secondDerivateList = deque([[0, 0], [0, 0], [0, 0], [0, 0], [0, 0]])
        self.derivate = 0
        self.secondDerivate = 0
        self.speed = 0
        self.maxSpeed = 0

    def getTrend(self, deque):
        n = len(deque)
        a = n * ((deque[0][0] * deque[0][1]) +
                 (deque[1][0] * deque[1][1]) +
                 (deque[2][0] * deque[2][1]) +
                 (deque[3][0] * deque[3][1]) +
                 (deque[4][0] * deque[4][1]))
        b = ((deque[0][0] + deque[1][0] +
              deque[2][0] + deque[3][0] +
              deque[4][0]) * (deque[0][1] +
                              deque[1][1] + deque[2][1] +
                              deque[3][1] + deque[4][1]))
        c = n * ((deque[0][0] ** 2) +
                 (deque[1][0] ** 2) +
                 (deque[2][0] ** 2) +
                 (deque[3][0] ** 2) +
                 (deque[4][0] ** 2))
        d = (((deque[0][0]) +
              (deque[1][0]) +
              (deque[2][0]) +
              (deque[3][0]) +
              (deque[4][0])) ** 2)
        m = ((a - b) / (c - d))
        return m

    def getNewSpeed(self, change):
        if change == -100:
            if self.speed > 12:
                self.speed = -100
            else:
                self.speed = 0
        else:
            if self.speed < 0:
                self.speed = 0
            newSpeed = self.speed + (change * 0.3)
            if newSpeed > self.maxSpeed:
                self.speed = self.maxSpeed
            elif newSpeed < 0:
                self.speed = 0
            else:
                if self.speed == 0 and newSpeed > 0:
                    self.speed = self.minSpeed
                elif self.speed > self.minSpeed and newSpeed < self.minSpeed:
                    self.speed = 0
                else:
                    self.speed = newSpeed

        return int(round(self.speed))

    def stop(self):
        self.speed = 0

    def evaluation(self, distance, deltaTime, appValues, oldValues):
        self.maxSpeed = appValues[0]

        self.derivateList.popleft()
        self.derivateList.append([deltaTime + self.derivateList[3][0], distance])

        self.secondDerivateList.popleft()
        self.secondDerivateList.append([deltaTime + self.derivateList[3][0], self.getTrend(self.derivateList)])
        self.derivate = self.getTrend(self.derivateList)
        self.secondDerivate = self.getTrend(self.secondDerivateList)

        print("distance = " + str(distance) + " | derivate = " + str(self.getTrend(self.derivateList)) +
              " | second derivate = " + str(self.getTrend(self.secondDerivateList)))

        if distance == 34.00: #Hard coded value :-)
            return [self.maxSpeed, appValues[1]]
        elif distance > self.y + self.errormargin:
            if self.derivate > 0 + self.derivateErrormargin:
                if self.secondDerivate > 0 + self.secondDerivateErrormargin:
                    return [self.getNewSpeed(5), appValues[1]]
                elif self.secondDerivate < 0 - self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET
                else:
                    return [self.getNewSpeed(2), appValues[1]]  # KA
            elif self.derivate < 0 - self.derivateErrormargin:
                if self.secondDerivate > 0 + self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET
                elif self.secondDerivate < 0 - self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # ANTINGEN INGET ELLER SAKTA IN
                else:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET
            else:
                if self.secondDerivate > 0 + self.secondDerivateErrormargin:
                    return [self.getNewSpeed(2), appValues[1]]  # KA
                elif self.secondDerivate < 0 - self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET
                else:
                    return [self.getNewSpeed(2), appValues[1]]  # KA

        elif self.derivate < self.derivateEmergencymargin: #EMERGENCY BRAKE
            return [self.getNewSpeed(-100), appValues[1]]
        elif distance < self.y - self.errormargin:
            if self.derivate > 0 + self.derivateErrormargin:
                if self.secondDerivate > 0 + self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # ANTINGEN GR INGET ELLER SAKTA IN
                elif self.secondDerivate < 0 - self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET
                else:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET

            elif self.derivate < 0 - self.derivateErrormargin:
                if self.secondDerivate > 0 + self.secondDerivateErrormargin:
                    return [self.getNewSpeed(-100), appValues[1]]  # BROMSA!
                elif self.secondDerivate < 0 - self.secondDerivateErrormargin:
                    return [self.getNewSpeed(-100), appValues[1]]  # BROMSA!
                else:
                    return [self.getNewSpeed(-100), appValues[1]]  # BROMSA!

            else:
                if self.secondDerivate > 0 + self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET!
                elif self.secondDerivate < 0 - self.secondDerivateErrormargin:
                    return [self.getNewSpeed(-100), appValues[1]]  # SAKTA IN
                else:
                    return [self.getNewSpeed(-100), appValues[1]]  # SAKTA IN


        else:
            if self.derivate > 0 + self.derivateErrormargin:
                if self.secondDerivate > 0 + self.secondDerivateErrormargin:
                    return [self.getNewSpeed(1), appValues[1]]  # KA EN DEL
                elif self.secondDerivate < 0 - self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET
                else:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET

            elif self.derivate < 0 - self.derivateErrormargin:
                if self.secondDerivate > 0 + self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET
                elif self.secondDerivate < 0 - self.secondDerivateErrormargin:
                    return [self.getNewSpeed(-2), appValues[1]]  # SAKTA IN EN DEL
                else:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET

            else:
                if self.secondDerivate > 0 + self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET
                elif self.secondDerivate < 0 - self.secondDerivateErrormargin:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET
                else:
                    return [self.getNewSpeed(0), appValues[1]]  # GR INGET

# pc = PIDController3(0.40, 0.10, 50)
# sh = SensorHandler.SensorHandler(34.00, 0.7)
# lastTime = time.time()

# while True:
#    sh.put(g.can_ultra)
#    currentTime = time.time()

#    pc.evaluation((currentTime - lastTime), sh.get())

#    lastTime = currentTime
#    time.sleep(0.1)
