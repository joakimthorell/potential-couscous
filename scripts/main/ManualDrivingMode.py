from DrivingMode import DrivingMode

class ManualDrivingMode(DrivingMode):

    def evaluation(self, distance, deltaTime, driveValue, steeringValue):
        return [driveValue, steeringValue]