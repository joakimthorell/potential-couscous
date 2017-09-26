class SensorHandler:

    """
    Filters sensor data into something useful and consistent. Usage: Create an instance and provide the max (default)
    value for the sensor and the max accepted value (currently 0.7) using
    "sensor = SensorHandler.SensorHandler(maxValue, maxAccepted)". Collect and store sensor data with the "put(value)"
    method, then retrieve the filtered data using "get()".
    """

    def __init__(self, maxValue, maxAccepted):
        self.numberOfHighValues = 0
        self.value = maxValue
        self.maxValue = maxValue
        self.maxAccepted = maxAccepted

    def put(self, value):
        if value < self.maxAccepted:
            self.numberOfHighValues = 0
            self.value = value
        else:
            self.numberOfHighValues += 1
            if self.numberOfHighValues >= 10:
                self.value = self.maxValue

    def get(self):
        return self.value

    #Unused
    @staticmethod
    def minValue(listOfValues):
        min = listOfValues[0]
        for x in listOfValues:
            if(x < min):
                min = x
        return min

    #Unused
    @staticmethod
    def mean(listOfValues):
        listSum = 0
        for x in listOfValues:
            listSum += x
        return listSum / len(listOfValues)
