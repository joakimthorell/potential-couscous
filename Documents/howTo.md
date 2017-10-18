### How to run the system

Create a fresh SD-card with TCU kernel. To do this, follow instructions [here](https://moped.sics.se/wordpress/?page_id=328).

SSH into car to start start edit.
```sh
$ ssh pi@yourIP
insert password > 'pi'
```

Install OpenCV on MOPED TCU. Follow instructions [here](https://github.com/felixnorden/moppepojkar/issues/38).

It is also neccecary to delete the optipos files.
```sh
$ cd
$ sudo rm -rf ../../etc/init.d/.
```

Clone this repo to MOPED home directory.
```sh
$ cd
$ git clone https://github.com/mattssonj/potential-couscous.git
$ cp -a potential-couscous/scripts/main .
```

Install the mobile app to Android 7.0 device. Use [apk](www.google.com)
Make sure both MOPED and phone is connected to same network and port 8888 is open in firewall.

Start the car. SSH into TCU and run following commands
```sh
$ python main/Main.py
```

Text should now appear in terminal. It's now time to connect the App to the Car.
Press the bluetooth logo in upper right corner and fill in MOPEDs IP-address. 
Press Connect and choose mode.
