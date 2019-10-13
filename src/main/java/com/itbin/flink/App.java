package com.itbin.flink;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer08;
import org.apache.flink.streaming.util.serialization.SimpleStringSchema;

import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {
    public static void main( String[] args ) throws Exception {

        System.out.println(111);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("zookeeper.connect", "localhost:2181");
        properties.setProperty("group.id", "flink_consumer");
        DataStream stream = env.addSource(new FlinkKafkaConsumer08<>(
                "test", new SimpleStringSchema(), properties) );
        stream.map(new MapFunction<String, String>() {
            private static final long serialVersionUID = -6867736771747690202L;
            @Override
            public String map(String value) throws Exception {
                return "Stream Value: " + value;
            }}).print();
        env.execute();
    }
}
