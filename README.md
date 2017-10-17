# Software Engineering Project DAT255, Autumn 2017

![PC Logo](/Documents/images/Logo.2.0..png)


#### What is this?
Vad har vi gjort? TODO


### `/Documents`

* Deliverbles
    * [D1. Strategies for SCRUM](https://github.com/mattssonj/potentialcouscous/blob/master/Documents/Deliverables/D1_Strategier%20för%20SCRUM.pdf)
    * [D2. Backlog](https://trello.com/b/gsIRwmhq/potential-couscous)
    * [D3. Half-time evaluation](https://github.com/mattssonj/potential-couscous/blob/master/Documents/Deliverables/D3%20Half-time%20evaluation.pdf)
* [Meeting protocols](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Meeting%20Protocols) - All our meeting protocols during the weeks
* [Retrospectives](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Retrospectives) - Our sprint retrospectives





[`/scripts`](/scripts/) - This is all the code we developed to make the MOPED work as intended. 

[`/app`](/app/) - Android application source code. For complete app download apk [here](www.google.com)


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
|Alexandra Garrido Jaque|CID|AlexAndraGarrido|
|Anne Keller|CID|kelleranne|
|Anton Hägermalm|CID|antonhager|
|Fredrik Viberg|CID|vibergf|
|Jakob Erlandsson|CID|JakobErlandsson|
|Jakob Wall|CID|jakwall|
|Joakim Mattsson|joamatt|mattssonj|
|Kevin Brunström|CID|Kevinbrunstrom|
|Philip Nord|CID|nordp|
|Sanjin Slavnic|CID|lasanjin|
|Simon Kärrman|CID|skarrman|
|Svante Bennhage|CID|Skroudelz|
