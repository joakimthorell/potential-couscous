from SensorHandler import SensorHandler

# Testing to see if sensorHandler filters away values above
# a certain threshold

sensorHandler = SensorHandler(34.0, 0.7)


sensorHandler.put(0.6)
sensorHandler.put(0.3)
sensorHandler.put(0.4)

test1 = sensorHandler.get() == 0.4
print("test 1 success " + str(test1))

sensorHandler.put(0.6)
sensorHandler.put(0.3)
sensorHandler.put(0.4)
sensorHandler.put(1.5)

test2 = sensorHandler.get() == 0.4
print("test 2 success " + str(test2))

sensorHandler.put(0.1)
sensorHandler.put(1)
sensorHandler.put(2)
sensorHandler.put(3)
sensorHandler.put(5)
sensorHandler.put(6)
sensorHandler.put(7)
sensorHandler.put(8)
sensorHandler.put(5)
sensorHandler.put(3)
sensorHandler.put(1)
sensorHandler.put(3)

test3 = sensorHandler.get() == sensorHandler.maxValue
print("test 3 success " + str(test3))

print("all tests " + str(test1 and test2 and test3))
