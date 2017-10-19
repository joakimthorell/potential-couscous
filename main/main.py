#!/usr/bin/python
# -*- coding: utf-8 -*-
import time
from subprocess import Popen

print("Initializing camera")
Popen(['sudo','modprobe','bcm2835-v4l2'])
time.sleep(1)
print("Camera initialized")
print("Initializing main")

from stringConverter import *
from python_server import start_server
from SensorHandler import SensorHandler
from SensorDataAdapter import SensorDataAdapter
from VCUSocket import VCUSocket
from ACCDrivingMode import ACCDrivingMode
from PlatoonDrivingMode import PlatoonDrivingMode
from ManualDrivingMode import ManualDrivingMode
import os
import sys


aMode = ACCDrivingMode()
pMode = PlatoonDrivingMode()
mMode = ManualDrivingMode()

print("Driving modes initialized")

sensorHandler = SensorHandler(34.00, 0.8, 20)
sensorDataAdapter = SensorDataAdapter()
vcuSocket = VCUSocket()

currentMode = mMode
appString = "V0000H0000"
oldValues = [0, 0]

print("Main initialized")

def handleStringValue(decodeString):
    print("App sent: " + decodeString)
    global currentMode
    global appString

    l = len(decodeString)

    if (l >= 10):
        if (decodeString[0] == 'V' and decodeString[5] == 'H'):
            appString = decodeString[0:10]
    elif (l >= 5):
        if (decodeString[0] == 'V'):
            appString = decodeString[0:5] + appString[5:10]
        elif (decodeString[0] == 'H'):
            appString = appString[0:5] + decodeString[0:5]
    elif (l >= 1):
        if (decodeString[0] == "m"):
            currentMode = mMode
            print("Mode: Manual")
        elif (decodeString[0] == "a"):
            currentMode = aMode
            print("Mode: ACC")
        elif (decodeString[0] == "p"):
            currentMode = pMode
            print("Mode: Platoon")

    return appString

start_server(handleStringValue)

print("Waking VCU..")
vcuSocket.send("V0000H0000")
time.sleep(1.5)

print("Main loop starting")
lastTime = time.time()
while (True):
    currentTime = time.time()

    sensorHandler.put(sensorDataAdapter.get())
    appValues = stringToTotalInt(appString)
    newValues = currentMode.evaluation(sensorHandler.get(), (currentTime - lastTime), appValues, oldValues)
    newString = intToTotalString(newValues[0], newValues[1])

    oldValues = [newValues[0], newValues[1]]
    lastTime = currentTime

    newString = newString.strip()
    #print("V = " + str(newValues[0]) + ", H = " + str(newValues[1]))
    if len(newString) == 10:
        print("'" + newString + "'")
        vcuSocket.send(newString)
    time.sleep(0.1)