from run import *
import SensorHandler

sh = SensorHandler.SensorHandler(34.00, 0.7)

while True:
    sh.put(g.can_ultra)
    print(str(g.can_ultra) + " - " + str(sh.get()))
    time.sleep(0.10)



