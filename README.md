# moneytransfer   [![Build Status](https://travis-ci.org/lorenzosax/moneytransfer.svg?branch=master)](https://travis-ci.org/lorenzosax/moneytransfer)
WebApplication with spring-boot 2.1.9 (with gradle)


See: [Arch Linux (systemd)](https://blog.frd.mn/how-to-set-up-proper-startstop-services-ubuntu-debian-mac-windows/)

vi /etc/systemd/system/moneytransfer.service
```
[Unit]
Description=MoneyTransfer SpringbootApp Service
[Service]
User=root
# The configuration file application.properties should be here:
#change this to your workspace
WorkingDirectory=/opt/apps/money-transfer
#path to executable. 
#executable is a bash script which calls jar file
ExecStart=/usr/bin/java -jar -Dspring.profiles.active=prod /opt/apps/money-transfer/moneytransfer.jar
SuccessExitStatus=143
TimeoutStopSec=10
Restart=on-failure
RestartSec=5
[Install]
WantedBy=multi-user.target
```

after that:

```
systemctl daemon-reload
```

and then can start/stop/restart service

```
systemctl start moneytransfer
```