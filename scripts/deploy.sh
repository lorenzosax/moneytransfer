ssh root@206.81.16.40 "mkdir /opt/apps/test1 && cd /opt/apps/money-transfer && java -jar -Dspring.profiles.active=prod moneytransfer-*.jar &"
exit 0