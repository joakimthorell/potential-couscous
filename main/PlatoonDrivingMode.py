#!/usr/bin/python
# -*- coding: utf-8 -*-
from DrivingMode import DrivingMode
from ACCDrivingMode import ACCDrivingMode
import cv2
import cv2 as cv
import numpy as np

class PlatoonDrivingMode(DrivingMode):

    kernel = np.ones((5, 5), np.uint8)

    # Take input from webcam
    cap = cv2.VideoCapture(0)


    # Reduce the size of video to 320x240 so rpi can process faster
    capWidth = 320
    capHeight = 240
    cap.set(3, capWidth)
    cap.set(4, capHeight)

    lastPos = 0;

    acc = ACCDrivingMode()


    def calculate_steering(self):
        buzz = 0
        _, frame = self.cap.read()

        # converting to HSV
        hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
        hue, sat, val = cv2.split(hsv)

        hmn = 31
        hmx = 42
        smn = 92
        smx = 194
        vmn = 51
        vmx = 201

        # Apply thresholding
        hthresh = cv2.inRange(np.array(hue), np.array(hmn), np.array(hmx))
        sthresh = cv2.inRange(np.array(sat), np.array(smn), np.array(smx))
        vthresh = cv2.inRange(np.array(val), np.array(vmn), np.array(vmx))

        # AND h s and v
        tracking = cv2.bitwise_and(hthresh, cv2.bitwise_and(sthresh, vthresh))

        # Some morpholigical filtering
        dilation = cv2.dilate(tracking, self.kernel, iterations=1)
        closing = cv2.morphologyEx(dilation, cv2.MORPH_CLOSE, self.kernel)
        closing = cv2.GaussianBlur(closing, (5, 5), 0)

        # Detect circles using HoughCircles
        circles = cv2.HoughCircles(closing, cv2.HOUGH_GRADIENT, 3, 500, param1=120, param2=50, minRadius=5,
                                   maxRadius=100)



        # Draw Circles
        if circles is not None:
            for i in circles[0, :]:
                cv2.circle(frame, (int(round(i[0])), int(round(i[1]))), int(round(i[2])), (0, 0, 255), 5)
                cv2.circle(frame, (int(round(i[0])), int(round(i[1]))), 2, (0, 0, 255), 10)
                xVal = round(i[0])

                xVal = xVal - self.capWidth / 2
                pos = round(xVal / (self.capWidth / 2) * 100)

                if pos > 100:
                    pos = 100
                elif pos < -100:
                    pos = -100

                if (pos - self.lastPos > 5 or pos - self.lastPos < -5):
                    self.lastPos = pos

        return int(round(self.lastPos))


    def evaluation(self, distance, deltaTime, appValues, oldValues):
        accValues = self.acc.evaluation(distance, deltaTime, appValues, oldValues)
        #Kanske fungerar. accValues borde innehålla det som ACC-scriptet tycker att vi ska göra i nuläget, så i det här
        #scriptet ska vi skicka vidare accValues[0] som första värde, och styrvärdet som kameran bestämt som andra värde.
        steering = self.calculate_steering()

        steering = steering + appValues[1]
        if(steering > 100):
            steering = 100
        elif(steering < -100):
            steering = -100

        return [accValues[0], steering]
        #return [0, steering]
