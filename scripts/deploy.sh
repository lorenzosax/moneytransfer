rsync -r --delete-after --quiet $TRAVIS_BUILD_DIR/build/libs/ root@206.81.16.40:/opt/apps/money-transfer
ssh root@206.81.16.40 "cd /opt/apps/money-transfer && java -jar -Dspring.profiles.active=prod moneytransfer-*.jar &"
exit 0