### How to run the system

Generate a SD-card with TCU kernel by following instructions [here](https://moped.sics.se/wordpress/?page_id=328) and configuring TCU for a new hotspot. Follow instructions [here](https://moped.sics.se/?page_id=127).

Use SSH command to establish connection with the MOPED in order to start start editing.
```sh
$ ssh pi@yourIP
insert password > 'pi'
```

Install OpenCV on MOPED TCU. Follow instructions [here](https://github.com/felixnorden/moppepojkar/issues/38).

It's neccecary to delete the optipos files.
```sh
$ cd
$ sudo rm -rf ../../etc/init.d/.
```

Clone this repo to the MOPED home directory.
```sh
$ cd
$ git clone https://github.com/mattssonj/potential-couscous.git
$ cp -a potential-couscous/main .
```

Install the mobile app on an Android 7.0 device. Use [APK](https://github.com/mattssonj/potential-couscous/blob/master/app/apk/CousCousDrive3.5.apk?raw=true).
Make sure MOPED and phone are connected to same network and port 8888 is open.

Run following commands while connected to MOPED with SSH command:
```sh
$ python main/main.py
```

Text should now appear in terminal. It's now time to connect the App to the MOPED.
Press the bluetooth logo in upper right corner and fill in MOPEDs IP-address. 
Press "Connect" and select mode.
