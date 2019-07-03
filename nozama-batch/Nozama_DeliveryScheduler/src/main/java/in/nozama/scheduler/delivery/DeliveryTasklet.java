package in.nozama.scheduler.delivery;

import in.nozama.scheduler.delivery.processor.OrderItemProcessor;
import in.nozama.scheduler.delivery.reader.OrderItemReader;
import in.nozama.scheduler.delivery.writer.OrderItemWriter;
import in.nozama.service.model.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeliveryTasklet implements Tasklet {

    private final static Logger LOG = LoggerFactory.getLogger(DeliveryTasklet.class);

    @Autowired
    OrderItemProcessor orderItemProcessor;

    @Autowired
    OrderItemReader orderItemReader;

    @Autowired
    OrderItemWriter orderItemWriter;


    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        //Call the Reader
        Order[] orders = orderItemReader.read();

        //Check if received data is greater than 1, else fail the job and complete.
        if(orders.length <= 0){
            stepContribution.setExitStatus(ExitStatus.FAILED);
            chunkContext.setComplete();
            return RepeatStatus.FINISHED;
            //throw new OrderNotFoundException("No Orders available for Delivery Scheduler to Process.");
        }
        //Call the Process to change the Order Status
        List<Order> modOrders = orderItemProcessor.process(orders);
        //Call the writer to call restTemplate to update the status.
        orderItemWriter.write(modOrders);

        //Finish the job, handle exception in the process and writer will fail the job.
        return RepeatStatus.FINISHED;
    }
}
