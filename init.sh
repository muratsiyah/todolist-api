bash -c 'while [[ "$(curl -s -o /dev/null -w ''%{http_code}'' couchbase:8091)" != "200" ]]; do sleep 5; done'
java -jar todolist-api.jar