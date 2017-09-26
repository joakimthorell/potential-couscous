class SensorHandler:

    """
    Filters sensor data into something useful and consistent. Usage: Create an instance and provide the max (default)
    value for the sensor and the max accepted value (currently 0.7) using
    "sensor = SensorHandler.SensorHandler(maxValue, maxAccepted)". Collect and store sensor data with the "put(value)"
    method, then retrieve the filtered data using "get()". Recommended frequency of data input is 10hz.
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
            if self.numberOfHighValues >= 10:  #Arbitrary number. Sending input at a high frequency will break this.
                self.value = self.maxValue

    def get(self):
        return self.value