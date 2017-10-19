from ACCDrivingMode import ACCDrivingMode
from stringConverter import *


accDrivingMove = ACCDrivingMode()

appValues = [20, 0]
oldValues = [40, 40]
dist = 8
deltaTime = 0.5

newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)
newValues = accDrivingMove.evaluation(dist, deltaTime, appValues, oldValues)


newString = intToTotalString(newValues[0], newValues[1])

print(newString)