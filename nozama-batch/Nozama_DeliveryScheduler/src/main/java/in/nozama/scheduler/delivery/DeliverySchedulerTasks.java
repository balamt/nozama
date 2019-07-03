package in.nozama.scheduler.delivery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Reference to the Web page:
 * https://dzone.com/articles/running-on-time-with-springs-scheduled-tasks
 */
@Component
public class DeliverySchedulerTasks {

	/*
	 * private static final Logger LOG =
	 * LoggerFactory.getLogger(DeliverySchedulerTasks.class);
	 * 
	 * @Autowired JobLauncher jobLauncher;
	 * 
	 * @Autowired
	 * 
	 * @Qualifier("deliveryJob") Job deliveryJob;
	 * 
	 * 
	 *//**
		 * Calling the Delivery Job with the specified milliseconds as fixesRate similar
		 * to the controller class, what differs the controller and this class is , This
		 * class can execute with specified interval of time continuously.
		 *
		 * @throws JobParametersInvalidException
		 * @throws JobExecutionAlreadyRunningException
		 * @throws JobRestartException
		 * @throws JobInstanceAlreadyCompleteException
		 *//*
			 * @Scheduled(fixedRate = 50000) public void callDeliveryJob() throws
			 * JobParametersInvalidException, JobExecutionAlreadyRunningException,
			 * JobRestartException, JobInstanceAlreadyCompleteException {
			 * 
			 * JobParameters parameters = new JobParametersBuilder() .addDate("date", new
			 * Date()) .addString("source", " order") .toJobParameters();
			 * 
			 * jobLauncher.run(deliveryJob, parameters);
			 * 
			 * LOG.info("Batch Processing Invoked"); }
			 */

}
