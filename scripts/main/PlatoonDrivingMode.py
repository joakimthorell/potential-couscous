from DrivingMode import DrivingMode

class PlatoonDrivingMode(DrivingMode):

    def evaluation(self, distance, deltaTime, driveValue, steeringValue):
        return [0, 0]