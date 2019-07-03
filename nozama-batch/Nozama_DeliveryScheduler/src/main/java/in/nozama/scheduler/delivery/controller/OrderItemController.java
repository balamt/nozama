package in.nozama.scheduler.delivery.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/deliveryscheduler")
public class OrderItemController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("deliveryJob")
    Job deliveryJob;

    @RequestMapping("/process")
    public String handle() throws Exception{

        JobParameters parameters = new JobParametersBuilder()
                .addDate("date", new Date())
                .addString("source", " order")
                .toJobParameters();

        jobLauncher.run(deliveryJob, parameters);

        return "Batch Processing Invoked";

    }

}
