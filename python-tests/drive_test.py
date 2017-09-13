import nav as n
from nav import *
from nav1 import whole4, pause, cont
from driving import stop, drive, steer
init()

time.sleep(1)

g.limitspeed = None

while(1>0):
        drive(100)
        time.sleep(2)
        drive(0)
        steer(100)
        time.sleep(1)
        steer(-100)
        time.sleep(2)
        drive(-100)
        time.sleep(2)
	drive(0)
        steer(100)
        time.sleep(1)
        steer(-100)
        time.sleep(2)
