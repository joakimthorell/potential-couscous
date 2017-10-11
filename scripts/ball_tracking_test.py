import cv2
import cv2 as cv
import numpy as np
import sys

kernel = np.ones((5, 5), np.uint8)

# Take input from webcam
cap = cv2.VideoCapture(0)

# Reduce the size of video to 320x240 so rpi can process faster
cap.set(3, 320)
cap.set(4, 240)

calib = False
if len(sys.argv) > 1:
    calib = True


def nothing(x):
    pass

# Current values
hmn = 30
hmx = 59
smn = 41
smx = 145
vmn = 85
vmx = 255

if calib:
    # Creating a windows for later use
    cv2.namedWindow('HueComp')
    cv2.namedWindow('SatComp')
    cv2.namedWindow('ValComp')
    cv2.namedWindow('closing')
    cv2.namedWindow('tracking')

    # Creating track bar for min and max for hue, saturation and value
    # You can adjust the defaults as you like
    cv2.createTrackbar('hmin', 'HueComp', hmn, 179, nothing)
    cv2.createTrackbar('hmax', 'HueComp', hmx, 179, nothing)

    cv2.createTrackbar('smin', 'SatComp', smn, 255, nothing)
    cv2.createTrackbar('smax', 'SatComp', smx, 255, nothing)

    cv2.createTrackbar('vmin', 'ValComp', vmn, 255, nothing)
    cv2.createTrackbar('vmax', 'ValComp', vmx, 255, nothing)



lastPos = 0;

def exitTest():
    cap.release()
    cv2.destroyAllWindows()

while (1):

    buzz = 0
    _, frame = cap.read()

    # converting to HSV
    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    hue, sat, val = cv2.split(hsv)
    if calib:
        # get info from track bar and appy to result
        hmn = cv2.getTrackbarPos('hmin', 'HueComp')
        hmx = cv2.getTrackbarPos('hmax', 'HueComp')

        smn = cv2.getTrackbarPos('smin', 'SatComp')
        smx = cv2.getTrackbarPos('smax', 'SatComp')

        vmn = cv2.getTrackbarPos('vmin', 'ValComp')
        vmx = cv2.getTrackbarPos('vmax', 'ValComp')

    # Apply thresholding
    hthresh = cv2.inRange(np.array(hue), np.array(hmn), np.array(hmx))
    sthresh = cv2.inRange(np.array(sat), np.array(smn), np.array(smx))
    vthresh = cv2.inRange(np.array(val), np.array(vmn), np.array(vmx))

    # AND h s and v
    tracking = cv2.bitwise_and(hthresh, cv2.bitwise_and(sthresh, vthresh))

    # Some morpholigical filtering
    dilation = cv2.dilate(tracking, kernel, iterations=1)
    closing = cv2.morphologyEx(dilation, cv2.MORPH_CLOSE, kernel)
    closing = cv2.GaussianBlur(closing, (5, 5), 0)

    # Detect circles using HoughCircles
    circles = cv2.HoughCircles(closing, cv2.HOUGH_GRADIENT, 2, 120, param1=120, param2=50, minRadius=10, maxRadius=0)
    # circles = np.uint16(np.around(circles))


    # Draw Circles
    if circles is not None:
        for i in circles[0, :]:
            cv2.circle(frame, (int(round(i[0])), int(round(i[1]))), int(round(i[2])), (0, 0, 255), 5)
            cv2.circle(frame, (int(round(i[0])), int(round(i[1]))), 2, (0, 0, 255), 10)
            xVal = round(i[0])

            #cap.get(3) <- frame width
            xVal = xVal - cap.get(3)/2
            pos = round(xVal / (cap.get(3)/2)*100)
            if (pos - lastPos > 5 or pos - lastPos < -5):
                print(pos)
                lastPos = pos;
            buzz = 1

                # you can use the 'buzz' variable as a trigger to switch some GPIO lines on Rpi :)
                # print buzz
                # if buzz:
                # put your GPIO line here

    if calib:
        # Show the result in frames
        cv2.imshow('HueComp', hthresh)
        cv2.imshow('SatComp', sthresh)
        cv2.imshow('ValComp', vthresh)
        cv2.imshow('closing', closing)
        cv2.imshow('tracking', frame)

    k = cv2.waitKey(5) & 0xFF
    if k == 27:
        exitTest()
        break


