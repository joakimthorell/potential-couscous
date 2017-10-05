from DrivingMode import DrivingMode

class ACCDrivingMode(DrivingMode):

    def evaluation(self, distance, deltaTime, driveValue, steeringValue):
        return [0, 0]