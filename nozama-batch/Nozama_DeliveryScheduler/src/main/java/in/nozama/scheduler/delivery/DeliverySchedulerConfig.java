package in.nozama.scheduler.delivery;

/**
 *
 * Reference https://dzone.com/articles/batch-processing-with-spring-batch-and-amqp-easier
 *
 */
//@Configuration
//@EnableBatchProcessing
public class DeliverySchedulerConfig {

   /* public final static String DELIVERY_QUEUE_NAME = "NOZAMA-DELIVERY-CHANGE-Q";

    //Spring amqp rabbitmq Connection Factory
    @Autowired
    private ConnectionFactory connectionFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    // Create new Bean which created the Queue if not created already, the second param is to make the queue durable
    @Bean
    public Queue deliveryQueue(){
        return new Queue(DELIVERY_QUEUE_NAME, true);
    }

    @Bean
    public RabbitTemplate deliveryQueueTemplate(){
        RabbitTemplate template = new RabbitTemplate(this.connectionFactory);
        template.setDefaultReceiveQueue(DELIVERY_QUEUE_NAME);
        return template;
    }


    @Bean
    public ItemReader<String> orderItemReader(){
        return new AmqpItemReader<>(this.deliveryQueueTemplate());
    }

    @Bean
    public ItemProcessor<String, Order> orderItemProcessor(){
        return new OrderItemProcessor();
    }

    @Bean
    public ItemWriter<Order> orderItemWriter(){
        return new OrderItemWriter();
    }

    @Bean
    public Step deliveryStatusUpdateStep(){

        *//**
         * Creating new Batch Step "DELIVERY_CHANGE_BATCH_STEP"
         * Then we set Chunk size as 1 (Specifying how many data it read, process and write
         * Then we also assign the Reader, Processor and Writer class/beans
         * and build a step
         *//*

        return this.stepBuilderFactory.get("DELIVERY_CHANGE_BATCH_STEP")
                .<String, Order> chunk(1)
                .reader(this.orderItemReader())
                .processor(this.orderItemProcessor())
                .writer(this.orderItemWriter())
                .build();
    }*/


}
