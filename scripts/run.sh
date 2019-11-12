#!/bin/bash

ssh root@206.81.16.40 <<EOF
    sudo systemctl stop tomcat
    sleep 10
    cd $CATALINA_HOME/webapps
    rm -R money*
    cp /opt/apps/money-transfer/*.war .
    sudo systemctl start tomcat
EOF
