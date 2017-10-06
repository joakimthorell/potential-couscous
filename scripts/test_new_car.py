import nav as n
import picamera as cam
from nav import *
from nav1 import whole4, pause, cont
from driving import stop, drive, steer


camera = cam.PiCamera()

camera.start_preview()

print('Showing camera preview on connected screen....')

init()

time.sleep(1)

g.limitspeed = None


itteration = 5
countDown = 5

print('Starting car test....')
print('Test will itterate ' + itteration + ' times')
print('HOLD YOUR HORSES!! The car will start to move. Make sure nothing is in front of the car.')


# Helper method to start a countdown
def countdown(sek: int):
    for var in sek:
        print(sek - var)
    print('starting')


# Test steering
def teststeering():
    print('Starting steering test')
    time.sleep(2)
    print('Steering full right')
    steer(100)
    time.sleep(2)
    print('Steering full left')
    steer(-100)
    time.sleep(2)
    print('Steering 0 position')
    steer(0)
    time.sleep(2)
    print('Steering test comlete')


# Testing wheel sensor
def testodometer(times:int):
    print('Testing odometer')
    for var in times:
        odometer = g.odometer
        if odometer != 0.0:
            print('odometer is not 0, that might be a good thing.')
            print('odometer value is: ' + odometer)
        time.sleep(1)


# Testing front sensor
def testultra(times:int):
    print('Testing ultra sensor')
    for var in times:
        ultrasensor = g.can_ultra
        if ultrasensor != 0.0:
            print('ultrameter is not 0, that might be a good thing.')
            print('ultrameter value is: ' + ultrasensor)
        time.sleep(1)




def testdriving():

    # testing going forward
    print('Starting drive test')
    time.sleep(2)
    print('Driving with speed 10 for 3 secounds.')
    countdown(4)
    print('Starts to drive')
    drive(10)
    # starts to test odometer while going forward
    testodometer(3)
    drive(0)

    # testing going backwards
    print('Hopefully the car moved... We will now try to reverse')
    countdown(4)
    print('Starts to reverse')
    drive(-10)
    # starts to test ultra while going backwards
    testultra(3)
    drive(0)
    print('Driving test complete')


for var in itteration:
    print('running ' + var + ' time')
    teststeering()
    testdriving()

print('Test is now complete!'
      'if there is any problems check the terminal window and make sure data is printed when it should')
print('Happy working with this car.')
