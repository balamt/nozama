package in.nozama.service;

/**
 * Reference https://codenotfound.com/spring-kafka-json-serializer-deserializer-example.html
 * Sending JSON Object via Kafka
 *
 */
/*@Configuration
@EnableKafka*/
public class OrderInfoConsumerConfig {

  /*  @Value("${kafka.server.path}")
    private String kafkaServerAddress;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public Map<String, Object> orderInfoUpdaterConsumerConfig(){
        Map<String, Object> props = new HashMap<>();

        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaServerAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        return props;
    }

    @Bean
    public ConsumerFactory<String, Order> orderInfoUpdaterConsumerFactory(){
        return new DefaultKafkaConsumerFactory<>(orderInfoUpdaterConsumerConfig(),
                new StringDeserializer(), new JsonDeserializer<>(Order.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Order> kafkaListenerContainerFactory(){
        ConcurrentKafkaListenerContainerFactory<String, Order> listenerFactory =
                new ConcurrentKafkaListenerContainerFactory<>();
        listenerFactory.setConsumerFactory(orderInfoUpdaterConsumerFactory());
        return listenerFactory;
    }*/

}
