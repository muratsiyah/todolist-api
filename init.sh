/bin/sh -c 'while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' couchbase:8091)" != "200" ]]; do echo "WAITING: COUCHBASE SERVER IS NOT READY, IT WILL TAKE 2-3 MINS!" && sleep 5; done'
echo "OK: COUCHBASE LOOKS READY, APPLICATION STARTING!"
sleep 10
java -jar todolist-api.jar
echo "OK: APPLICATION STARTED!"