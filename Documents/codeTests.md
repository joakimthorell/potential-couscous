## Details about code quality

### FindBugs
We tested the Android application with FindBugs. Results can be seen on the image below:

![findBugs](/Documents/Images/findbugs.png)

One error emerged. FindBugs describes the reason as followed:
![findBugsReason](/Documents/Images/findbugsReason.png)

We choose not to adress this error as our code was inteded for the mainprogram only. This would become an issue if someone were to change the code running on the MOPED, responsible for receiving instructions from the app.

### PyLint
In order to control the car by using already existing functionality provided on the MOPED, all code (excluding the Android application) was written in Python. In order to generate functional scripts and work around some of the issuses caused by the available functionality, PyLint weren't used as a standard quality measurment. Focus was instead on testing Python scripts responsible for executing commands and controlling the MOPED by running them directly on the MOPED system to ensure desired output.
