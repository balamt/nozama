package in.nozama.kafka.order.updater;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import in.nozama.service.entity.Order;

import java.util.HashMap;
import java.util.Map;

/**
 * Reference https://codenotfound.com/spring-kafka-json-serializer-deserializer-example.html
 * Sending JSON Object via Kafka
 *
 */
@Configuration
public class OrderInfoProducerConfig {

    @Value("${kafka.server.path}")
    private String kafkaServerAddress;

    @Bean
    public Map<String, Object> orderInfoUpdaterProducerConfig(){
        Map<String, Object> props = new HashMap<>();

        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerAddress);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return props;
    }

    @Bean
    public ProducerFactory<String, Order> orderInfoUpdaterProducerFactory(){
        return new DefaultKafkaProducerFactory<>(orderInfoUpdaterProducerConfig());
    }

    @Bean
    public KafkaTemplate<String, Order> kafkaTemplate(){
        return new KafkaTemplate<>(orderInfoUpdaterProducerFactory());
    }

}
