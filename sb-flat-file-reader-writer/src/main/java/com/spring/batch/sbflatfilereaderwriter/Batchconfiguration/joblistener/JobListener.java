package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.joblistener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener implements JobExecutionListener {

   @Override
   public void beforeJob (JobExecution jobExecution) {

   }

   @Override
   public void afterJob (JobExecution jobExecution) {

   }
}
