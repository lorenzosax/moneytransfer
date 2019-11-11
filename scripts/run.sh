#!/bin/bash

ssh root@206.81.16.40 <<EOF
  cd /opt/apps/money-transfer
  java -jar -Dspring.profiles.active=prod moneytransfer-*.jar &
EOF
