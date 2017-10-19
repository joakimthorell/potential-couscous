from abc import ABCMeta, abstractmethod

class DrivingMode:
    __metaclass__ = ABCMeta

    @abstractmethod
    def evaluation(self, distance, deltaTime, appValues, oldValues):
        pass