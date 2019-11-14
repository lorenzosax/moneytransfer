#!/bin/bash

ssh root@206.81.16.40 <<EOF
  sudo systemctl restart moneytransfer
EOF