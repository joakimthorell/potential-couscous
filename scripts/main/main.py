from stringConverter import *
from python_server import start_server
from SensorHandler import SensorHandler
from SensorDataCollector import SensorDataCollector
from VCUSocket import VCUSocket
from ACCDrivingMode import ACCDrivingMode
from PlatoonDrivingMode import PlatoonDrivingMode
from ManualDrivingMode import ManualDrivingMode
import time

aMode = ACCDrivingMode()
pMode = PlatoonDrivingMode()
mMode = ManualDrivingMode()

sensorHandler = SensorHandler(34.00, 0.7)
sensorDataCollector = SensorDataCollector()
vcuSocket = VCUSocket()

currentMode = mMode
vcuString = "V0000H0000"


def handleStringValue(decodeString):
    global currentMode
    global vcuString

    l = len(decodeString)

    if (l >= 10):
        if (decodeString[0] == 'V' and decodeString[5] == 'H'):
            vcuString = decodeString[0:10]
    elif (l >= 5):
        if (decodeString[0] == 'V'):
            vcuString = decodeString[0:5] + vcuString[5:10]
        elif (decodeString[0] == 'H'):
            vcuString = vcuString[0:5] + decodeString[0:5]
    elif (l >= 1):
        if (decodeString[0] == "m"):
            currentMode = mMode
        elif (decodeString[0] == "a"):
            currentMode = aMode
        elif (decodeString[0] == "p"):
            currentMode = pMode

    return vcuString

start_server(handleStringValue)

lastTime = time.time()
while (True):
    currentTime = time.time()

    sensorHandler.put(sensorDataCollector.get())

    oldValues = stringToTotalInt(vcuString)
    newValues = currentMode.evaluation(sensorHandler.get(), (currentTime - lastTime), oldValues[0], oldValues[1])
    vcuString = intToTotalString(newValues[0], newValues[1])

    lastTime = currentTime

    #Ifall vi inte hanterar datan. Halvbra lösning :-)
    if currentMode != mMode:
        vcuSocket.send(vcuString)
    time.sleep(0.1)
