class SensorHandler:

    def __init__(self, maxValue):
        self.valueList = [maxValue, maxValue, maxValue, maxValue, maxValue, maxValue, maxValue, maxValue, maxValue, maxValue]
        self.valueIndex = 0
        self.maxValue = maxValue

    def put(self, value):
        self.valueList[self.valueIndex] = value
        self.valueIndex = (self.valueIndex + 1) % len(self.valueList)

    def get(self):
        return SensorHandler.minValue(self.valueList)

    @staticmethod
    def mean(listOfValues):
        listSum = 0
        for x in listOfValues:
            listSum += x
        return listSum / len(listOfValues)

    @staticmethod
    def minValue(listOfValues):
        min = listOfValues[0]
        for x in listOfValues:
            if(x < min):
                min = x
        return min
