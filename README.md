# SpringBootApacheKafkaHelloWorld
start kafka zoopkeeper - 
root@kafka-vm:/home/dnm_gcp3_1984# cd kafka_2.12-3.1.0
root@kafka-vm:/home/dnm_gcp3_1984/kafka_2.12-3.1.0# bin/zookeeper-server-start.sh config/zookeeper.properties

start kafka server - 
root@kafka-vm:/home/dnm_gcp3_1984/kafka_2.12-3.1.0# 
root@kafka-vm:/home/dnm_gcp3_1984/kafka_2.12-3.1.0# bin/kafka-server-start.sh config/server.properties


Start consumer:
bin/kafka-console-consumer.sh --topic quickstart-events --from-beginning --bootstrap-server localhost:9092
