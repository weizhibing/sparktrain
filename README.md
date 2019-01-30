# kafka
kafka配置过程
1.单节点单broker部署及使用
	第一步：启动zookeeper ./zkServer.sh start
	启动kafka.  kafka-server-start.sh $KAFKA_HOME/config/server.properties 
	(生产消息)创建 topic kafka-topics.sh --create --zookeeper cn:2181 --replication-factor 1 --partitions 1 --topic hello_topic
	发送消息 kafka-console-producer.sh --broker-list cn:9092 --topic hello_topic
	消费消息 kafka-console-consumer.sh --zookeeper cn:2181 --topic hello_topic --from-beginning


	查看所有topic   kafka-topics.sh --list --zookeeper cn:2181
 	查看所有topic的详细信息   kafka-topics.sh --describe --zookeeper cn:2181
	查看指定topic的详细信息	   kafka-topics.sh --describe --zookeeper cn:2181 topic hello_topic
2.单节点多broker部署及使用
	Server-1.properties
	Server-2.properties
	Server-3.properties

	执行1:kafka-server-start.sh -daemon $KAFKA_HOME/config/server-1.properties &
	执行2:kafka-server-start.sh -daemon $KAFKA_HOME/config/server-2.properties &
	执行3:kafka-server-start.sh -daemon $KAFKA_HOME/config/server-3.properties &
	
	创建topic kafka-topics.sh --create --zookeeper cn:2181 --replication-factor 3 --partitions 1 --topic my-replicated-topic
	发送消息 kafka-console-producer.sh --broker-list cn:9093,cn:9094,cn:9095 --topic my-replicated-topic
	消费消息 kafka-console-consumer.sh --zookeeper cn:2181 --topic my-replicated-topic

