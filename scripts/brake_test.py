import nav as n
from nav import *
from nav1 import whole4, pause, cont
from driving import stop, drive, steer
init()

time.sleep(1)

g.limitspeed = None

def manualtest(speed, brakingforce, steerdefault):
        steer(steerdefault)
        input('press enter to drive')
        drive(speed)
        input('press enter for the car to brake')
        timestamp = time.time()
        print('the car is braking')
        drive(brakingforce)
        input('press enter when the car stops')
        brakingtime = time.time() - timestamp
        print('brakingtime: ' + str(brakingtime))
        time.sleep(5)
        drive(0)


def automatictest(v0, v1):
        steer(-100)
        nav_signal.setleds(0,7)
        drive(v0/3)
        time.sleep(2)
        drive(v0*2/3)
        time.sleep(2)
        drive(v0)
        time.sleep(10)
        while True:
                time.sleep(0.0001)
                if g.ang%360 < 5:
                        nav_signal.setleds(0,0)
                        o0 = g.odometer
                        a0 = g.ang
                        drive(v1)
                        break
        time.sleep(5)
        o1 = g.odometer
        a1 = g.ang
        print((o1-o0, a1-a0))

def constantmanualtest(speed, brakingforce, steerdefault):
        steer(steerdefault)
        input('press enter to drive')
        drive(speed)
        input('press enter for the car to brake')
        timestamp = time.time()
        print('the car is braking')
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        drive(brakingforce)
        input('press enter when the car stops')
        brakingtime = time.time() - timestamp
        print('brakingtime: ' + str(brakingtime))
        time.sleep(5)
        drive(0)
        