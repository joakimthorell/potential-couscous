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
    * [D.5 Report](/Documents/Deliverables/D5_Reflection_Report.pdf)
    * Extras
      * [Vision](/Documents/Deliverables/Our_vision_statement.pdf) - Our vision
      * [Contract](/Documents/GroupContract.pdf) - Group Contract
      
* [Meeting protocols](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Meeting%20Protocols) - Weekly meeting protocols.
* [Retrospectives](https://github.com/mattssonj/potential-couscous/tree/master/Documents/Retrospectives) - Weekly sprint retrospectives


### Code
   * [`/main`](/main/) - Contains the main program developed in order to make the MOPED work as intended. 
   * [`/tests`](/tests/) - Contains different test scripts used to test both hardware and software.
   * [`/app`](/app/) - Android application source code. For complete app download APK [here](https://github.com/mattssonj/potential-couscous/blob/master/app/apk/CousCousDrive3.5.apk?raw=true). Make sure your device can run API 25 or higher.


### Overview
<p align="center"><img src="/Documents/Images/MOPED.jpg"></p>

1. A Computer, the MOPED and a mobile device need to be connected to the same hotspot.
All data between devices is sent through local network. 

2. In order to run the [main.py](/main/main.py) file you need to connect to the MOPED using ssh command from your computer. 
See the [How-To](/Documents/howTo.md).

3. You can connect your phone to the MOPED when main.py is running by using the [CousCousDrive](/Documents/appImages.md) application.
The app sends data to CousCous [server](/main/python_server.py), telling main.py to execute different scripts and functions and generate desired output depending on mode. App will change UI depending on mode.
Mobile device connects to MOPED by entering MOPED's ip-address (`port 8888` needs to be open).

4. The [main.py](/main/main.py) system is developed with Python 2.7. It runs the car by sending data to [ecm socket](https://github.com/sics-sse/moped/tree/master/ecm-core/src/main/java) at `localhost port 9000`.
System changes between 3 diffrent states: Manual, ACC and Platooning.

5. The [`/main`](/main/) system collects data from the CAN-Bus. Data from the Ultra Sonic Sensor is used to execute Platooning and ACC mode.


### How to run the system
Follow instructions [here](/Documents/howTo.md).


### External libraries
[OpenCV](https://opencv.org) was used in order to execute platooning by helping the MOPED find an object to follow. Other libraries were mostly graphical in order to make the app more user friendly (See [here](/Documents/androidLibraries.md)).


### Code tests and quality insurance 
During the development we created [`/tests/`](/tests/) for some of our code. Additional info can be found [here](/tests/Allmänna%20tester%20(Read%20this).pdf). We also performed some given tests eg. [FindBugs](http://findbugs.sourceforge.net) on our java files(Android application) and were presented to perform [PyLint](https://www.pylint.org) on our Python Code. The FindBugs results can be seen [here](/Documents/codeTests.md). In summary there was one inisignificant issue (read the results for more info). Regarding PyLint, we choose not to perform these tests since it wasn't a necessity (read [here](/Documents/codeTests.md) for further explanation).


### Gitinspector
[Gitinspector](https://github.com/ejwa/gitinspector) was used to measure the amount of code written by each team member. Bare in mind that most code was written pairwise, therefore these statistics are misleading. See [result](/Documents/gitinspector.md)


### Contributors
| Name | CID | GitHub |
|------|-----|--------|
|Alexandra Garrido Jaque|jaque|AlexAndraGarrido|
|Anne Keller|annekel|kelleranne|
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
