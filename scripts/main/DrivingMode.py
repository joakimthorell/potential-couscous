from abc import ABC, abstractmethod

class DrivingMode(ABC):

    @abstractmethod
    def evaluation(self, distance, deltaTime, driveValue, steeringValue):
        pass