# Software Engineering Project DAT255, Autumn 2017


### Team
<img src="/Documents/images/Logo.2.0..png">


### Description
This is a repository for a software engineering project at Chalmers University of Technology. The project consist of developing software for autonomous platooning implemented on a Mobile Open Platform Development ([MOPED](https://github.com/sics-sse/moped)) unit under real-life working conditions with the purpose of learning and implementing Scrum.


### Documents

* Deliverbles
    * [D1. Strategies for SCRUM](/Documents/Deliverables/D1_Stratergies_for_scrum.pdf)
    * [D2. Backlog](https://trello.com/b/gsIRwmhq/potential-couscous)
    * [D3. Half-time evaluation](/Documents/Deliverables/D3_HalfTime_Evaluation.pdf)
* [Meeting protocols](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Meeting%20Protocols) - All our meeting protocols during the weeks
* [Retrospectives](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Retrospectives) - Our sprint retrospectives





### Code
   * [`/scripts`](/scripts/) - All code we developed to make the MOPED work as intended. 
   * [`/app`](/app/) - Android application source code. For complete app download APK [here](www.google.com)


### How to run the system
To run the system follow [instructions](/Documents/howTo.md).

### Development
<p align="center"><img src="/Documents/images/MOPED.jpg"></p>

1. Computer, MOPED and mobile device needs to be connected to the same hotspot.
All data between devices is sent through local network. 

2. To run the Main.py file you need to SSH into the MOPED from a computer. 
See the [HowTo](/Documents/howTo.md) if needed. 

3. When Main.py is turned on you can connect your phone by using the CousCousDrive application.
The app will change UI depending on the mode and sends data to the Main system.
Make sure you are connected to the same network as p.1 pointed out. (`port 8888`needs to be open)

4. The Main.py system is built on Python2.7 and drives the car by sending data to [ecm socket](https://github.com/sics-sse/moped/tree/master/ecm-core/src/main/java) at `localhost port 9000`.
System changes between 3 diffrent states, Manual, ACC, Platooning. 

5. The Main system collects data from the CAN-Bus. This data (Ultra Sonic Sensor) are then used to help calculate situations in platooning and ACC mode. 


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
