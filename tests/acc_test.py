import nav as n
from nav import *
from nav1 import whole4, pause, cont
from driving import stop, drive, steer
init()

time.sleep(1)

g.limitspeed = None
print(g.can_ultra)
steer(-40)
while(True):
	print(g.can_ultra)
	if (g.can_ultra < 1):
        	drive(0)
	else:
		drive(25)
	time.sleep(0.1)
