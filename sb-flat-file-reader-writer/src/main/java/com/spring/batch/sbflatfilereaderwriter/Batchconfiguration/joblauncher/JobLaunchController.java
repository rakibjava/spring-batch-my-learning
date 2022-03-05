package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.joblauncher;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping (value = "/rwfile")
@EnableAsync
public class JobLaunchController {

   private static final Logger loger = LoggerFactory.getLogger ( JobLaunchController.class );

   private final JobLauncher jobLauncher;
   private final Job fileReadWriteJob;

   @Autowired
   public JobLaunchController (JobLauncher jobLauncher,Job fileReadWriteJob) {
      this.jobLauncher = jobLauncher;
      this.fileReadWriteJob = fileReadWriteJob;
   }

   @RequestMapping (value = "/job")
   public String runBatchJob () throws JobInstanceAlreadyCompleteException,
           JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

      runJob ( );
      loger.info ( "submitted successfully." );
      return "submitted successfully.";

   }

   @Async
   public void runJob () throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException,
           JobParametersInvalidException, JobRestartException {

      JobParameters jobParameter = new JobParametersBuilder ()
              .addDate ( "date", new Date (), true )
              .toJobParameters ();

      JobExecution jobExecution = jobLauncher.run ( fileReadWriteJob, jobParameter );
      loger.info ( String.valueOf ( jobExecution.getStatus () ) );

   }

}
