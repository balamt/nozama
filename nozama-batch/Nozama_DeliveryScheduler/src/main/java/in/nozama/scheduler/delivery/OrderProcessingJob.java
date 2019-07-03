package in.nozama.scheduler.delivery;

import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class OrderProcessingJob extends JobExecutionListenerSupport {

  /*  private static final Logger LOG = LoggerFactory.getLogger(OrderProcessingJob.class);

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    OrderItemProcessor orderItemProcessor;

    @Autowired
    OrderItemReader orderItemReader;

    @Autowired
    OrderItemWriter orderItemWriter;

    @Bean(name = "orderJob")
    public Job orderProcessingJob(){

        Step step = stepBuilderFactory.get("order-process-step-1")
                .<Order[], List<Order>> chunk(1)
                .reader(orderItemReader)
                .processor(orderItemProcessor)
                .writer(orderItemWriter)
                .build();

        Job job = jobBuilderFactory.get("orderprocessing-job")
                .incrementer(new RunIdIncrementer())
                .listener(this)
                .start(step)
                .build();
        return job;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //super.afterJob(jobExecution);
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
                LOG.info("BATCH JOB COMPLETED SUCCESSFULLY");
        }
    }*/
}
