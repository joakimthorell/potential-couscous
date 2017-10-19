from DrivingMode import DrivingMode

class ManualDrivingMode(DrivingMode):

    def evaluation(self, distance, deltaTime, appValues, oldValues):
        return [appValues[0], appValues[1]]