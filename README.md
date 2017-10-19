## Software Engineering Project DAT255, Autumn 2017


### Team
<img src="/Documents/Images/Logo.2.0..png">


### Description
This is a repository for a software engineering project at Chalmers University of Technology. The project consist of developing software for autonomous platooning implemented on a Mobile Open Platform Development ([MOPED](https://github.com/sics-sse/moped)) unit under real-life working conditions with the purpose of learning and implementing Scrum.


### Documents
* Deliverables
    * [D1. Strategies for SCRUM](/Documents/Deliverables/D1_Stratergies_for_scrum.pdf)
    * [D2. Backlog](https://trello.com/b/gsIRwmhq/potential-couscous)
    * [D3. Half-time evaluation](/Documents/Deliverables/D3_HalfTime_Evaluation.pdf)
    * D4. Presentation at Lindholmen
    * D5. --- Coming Soon ---
    * Extras
      * [Vision](/Documents/Deliverables/Our_vision_statement.pdf) - Our vision
      * [Contract](/Documents/GroupContract.pdf) - Group Contract
      
* [Meeting protocols](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Meeting%20Protocols) - Weekly meeting protocols.
* [Retrospectives](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Retrospectives) - Weekly sprint retrospectives


### Code
   * [`/scripts`](/scripts/) - All code we developed to make the MOPED work as intended. 
   * [`/app`](/app/) - Android application source code. For complete app download APK [here](www.google.com). Make sure your device can run API 25 or higher.


### How to run the system
Follow instructions: [instructions](/Documents/howTo.md).


### Development
<p align="center"><img src="/Documents/Images/MOPED.jpg"></p>

1. A Computer, the MOPED and a mobile device need to be connected to the same hotspot.
All data between devices is sent through local network. 

2. In order to run the [Main.py](/scripts/main/Main.py) file you need to connect to the MOPED using ssh command from your computer. 
See the [How-To](/Documents/howTo.md). 

3. You can connect your phone to the MOPED when Main.py is running by using the CousCousDrive application([images](/Documents/appImages.md)).
The app sends data to CousCous Server, telling Main.py to run different scripts depending on mode. App will change UI depending on mode.
Enter MOPED's ip-address (`port 8888` needs to be open) and make sure you are connected to the same network.

4. The [Main.py](/scripts/main/Main.py) system is developed with Python 2.7. It runs the car by sending data to [ecm socket](https://github.com/sics-sse/moped/tree/master/ecm-core/src/main/java) at `localhost port 9000`.
System changes between 3 diffrent states: Manual, ACC and Platooning.

5. The [`/scripts/main`](/scripts/main/) system collects data from the CAN-Bus. Data from the Ultra Sonic Sensor is used to execute Platooning and ACC mode.

### External libraries
In our project we only used a handfull of external libraries. Most of them are just graphical libraries to help the app look better but there is one more important one, [OpenCV](https://opencv.org). This library is used when platooning is activated to help us find an object to follow. 


The rest of the libraries are used in the app, these are:
- [VirtualJoystick](https://github.com/controlwear/virtual-joystick-android) - Used to create the Joystick.
- [ToggleButtons](https://github.com/rcketscientist/ToggleButtons) - Creates the 3 buttons in the bottom.
- [SpeedView](https://github.com/anastr/SpeedView) - Creates the speedometer around the Joystick.
- [RippleBackground](https://github.com/skyfishjy/android-ripple-background) - Creates the rippled background when pressing Play in platoon mode.

### Gitinspector
To evaluate team effort we used the tool [Gitinspector](https://github.com/ejwa/gitinspector).

See [result](/Documents/gitinspector.md)


### Contributors
| Name | CID | GitHub |
|------|-----|--------|
|Alexandra Garrido Jaque|jaque|AlexAndraGarrido|
|Anne Keller|annkel|kelleranne|
|Anton Hägermalm|anthage|antonhager|
|Fredrik Viberg|vibergf|vibergf|
|Jakob Erlandsson|erjakob|JakobErlandsson|
|Jakob Wall|jwall|jakwall|
|Joakim Mattsson|joamatt|mattssonj|
|Kevin Brunström|kevinbr|Kevinbrunstrom|
|Philip Nord|nordp|nordp|
|Sanjin Slavnic|slavnic|lasanjin|
|Simon Kärrman|simkarr|skarrman|
|Svante Bennhage|svaben|Skroudelz|
