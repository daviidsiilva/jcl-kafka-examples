
./kafka-2.3.0/bin/kafka-server-stop.sh kafka-2.3.0/config/server.properties
./kafka-2.3.0/bin/zookeeper-server-stop.sh kafka-2.3.0/config/zookeeper.properties

rm -rf /tmp/logs

./kafka-2.3.0/bin/zookeeper-server-start.sh kafka-2.3.0/config/zookeeper.properties && ./kafka-2.3.0/bin/kafka-server-start.sh kafka-2.3.0/config/server.properties 
