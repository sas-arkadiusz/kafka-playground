package pl.encodeme;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class ProducerDemo {
    private static final Logger log = LoggerFactory.getLogger(ProducerDemo.class.getSimpleName());

    public static void main(String[] args) {
        log.info("Hello World!");

        // 1. Create Producer Properties
        // 1.1. Connect to the localhost
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");

        // 1.2. Set the Producer properties
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());

        // 2. Create the Producer
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 2.1. Create a Producer Record
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("demo_topic", "hello world");

        // 3. Send the data
        producer.send(producerRecord);

        // 4. Flush and close the Producer (tell the Producer to send all the data and block until done)
        producer.flush();

        // 4.1. Close the Producer (.close() also calls .flush() method)
        producer.close();

        /* 5. Check the results:

            5.1. Create a topic.
            kafka-topics --create \
            --bootstrap-server localhost:9092 \
            --replication-factor 1 \
            --partitions 1 \
            --topic demo_topic

            5.2. Get the messages from the topic (run the command and then the code)
            kafka-console-consumer --bootstrap-server localhost:9092 --topic demo_topic --from-beginning
         */
    }
}
