from ManualDrivingMode import ManualDrivingMode

#Testing to see if we return the same steering values after they have been evalued

testArray = [13, 37]
testArrayDos = [73, 31]
testArrayTres = [1, 3]
manualDrivingMode = ManualDrivingMode()

def manualValueTest(testingArray):


    inTestArray = manualDrivingMode.evaluation(10, 10, testingArray, testArrayDos)


    if testingArray[0] == inTestArray[0] and testingArray[1] == inTestArray[1]:
        print "Success"
    else:
        print "Failure"


manualValueTest(testArray)
manualValueTest(testArrayDos)
manualValueTest(testArrayTres)
