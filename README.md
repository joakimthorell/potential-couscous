# Software Engineering Project DAT255, Autumn 2017

<p align="center"> <img src="/Documents/images/Logo.2.0..png"> </p>


### Description
This is a repository for a software engineering project at Chalmers University of Technology. The project consist of developing software for autonomous platooning implemented on a Mobile Open Platform Development ([MOPED](https://github.com/sics-sse/moped)) unit under real-life working conditions with the purpose of learning and implementing Scrum.


### Documents

* Deliverbles
    * [D1. Strategies for SCRUM](https://github.com/mattssonj/potentialcouscous/blob/master/Documents/Deliverables/D1_Strategier%20för%20SCRUM.pdf)
    * [D2. Backlog](https://trello.com/b/gsIRwmhq/potential-couscous)
    * [D3. Half-time evaluation](https://github.com/mattssonj/potential-couscous/blob/master/Documents/Deliverables/D3%20Half-time%20evaluation.pdf)
* [Meeting protocols](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Meeting%20Protocols) - All our meeting protocols during the weeks
* [Retrospectives](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Retrospectives) - Our sprint retrospectives





### Code
   * [`/scripts`](/scripts/) - This is all the code we developed to make the MOPED work as intended. 
   * [`/app`](/app/) - Android application source code. For complete app download apk [here](www.google.com)


### How to run the system

To make the [MOPED](https://github.com/sics-sse/moped) run with our created files their are some dependencies.

Install OpenCV on MOPED TCU. Follow instructions [here](https://github.com/felixnorden/moppepojkar/issues/38).

It is also neccecary to delete the optos files.
```sh
$ cd
$ rm -rf /something/way/to/optipos
```

Clone this repo to MOPED home directory.
```sh
$ git clone https://github.com/mattssonj/potential-couscous.git
$ cd
$ cp -a potential-couscous/scripts/main .
```

Install the mobile app to Android 7.0 device. Use [apk](www.google.com)

Start the car. SSH into TCU and run following commands
```sh
$ sudo modprobe bcm2835-v4l2
$ python main/Main.py
```
Text should now appear in terminal. It's now time to connect the App to the Car.
Press the bluetooth logo in upper right corner and fill in MOPEDs IP-address. 
Press Connect and choose mode.


### Contributors

| Name | CID | GitHub |
|------|-----|--------|
|Alexandra Garrido Jaque|jaque|AlexAndraGarrido|
|Anne Keller|annkel|kelleranne|
|Anton Hägermalm|anthage|antonhager|
|Fredrik Viberg|vibergf|vibergf|
|Jakob Erlandsson|erjakob|JakobErlandsson|
|Jakob Wall|CID|jakwall|
|Joakim Mattsson|joamatt|mattssonj|
|Kevin Brunström|kevinbr|Kevinbrunstrom|
|Philip Nord|nordp|nordp|
|Sanjin Slavnic|slavnic|lasanjin|
|Simon Kärrman|simkarr|skarrman|
|Svante Bennhage|svaben|Skroudelz|
