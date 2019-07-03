package in.nozama.scheduler.delivery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class DeliveryTaskletConfig {

    private final static Logger LOG = LoggerFactory.getLogger(DeliveryTaskletConfig.class);

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    DeliveryTasklet deliveryTasklet;

    @Bean
    protected Step runDeliveryStep(){

        LOG.info("******************************Inside runDeliveryStep *********************************");

        return stepBuilderFactory.get("delivery-status-step")
                .tasklet(deliveryTasklet)
                .build();
    }

    @Bean(name = "deliveryJob")
    public Job job(){
        LOG.info("****************************** job *********************************");
        return jobBuilderFactory.get("delivery-status-job")
                .start(runDeliveryStep())
                .build();
    }


}
