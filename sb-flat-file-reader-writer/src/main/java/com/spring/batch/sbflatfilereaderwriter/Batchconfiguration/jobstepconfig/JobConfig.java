package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.jobstepconfig;

import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.Employee;
import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.EmployeeInfo;
import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.joblistener.FileReaderListener;
import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.joblistener.FileWriterListener;
import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.jobprocessor.EmployeeProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {
   private static final Logger log = LoggerFactory.getLogger ( JobConfig.class );

   private final JobBuilderFactory jobBuilderFactory;
   private final StepBuilderFactory stepBuilderFactory;
   private final EmployeeProcessor employeeProcessor;
   private final FlatFileItemReader<Employee> csvReader;
   private final FlatFileItemWriter<EmployeeInfo> csvWriter;
   private final FileWriterListener writerListener;
   private final FileReaderListener readerListener;


   public JobConfig (JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory,
                     EmployeeProcessor employeeProcessor, FlatFileItemReader<Employee> csvReader,
                     FlatFileItemWriter<EmployeeInfo> csvWriter, FileWriterListener writerListener,
                     FileReaderListener readerListener) {

      this.jobBuilderFactory = jobBuilderFactory;
      this.stepBuilderFactory = stepBuilderFactory;
      this.employeeProcessor = employeeProcessor;
      this.csvReader = csvReader;
      this.csvWriter = csvWriter;
      this.writerListener = writerListener;
      this.readerListener = readerListener;

   }

   @Bean
   @Qualifier (value = "fileReadWriteJob")
   public Job flatFileReadWriteJob () {
      return jobBuilderFactory.get ( "fileReadWriteJob" )
              .incrementer ( new RunIdIncrementer () )
              .start ( flatFileReaderWriterStep () )
              .build ();
   }

   @Bean
   public Step flatFileReaderWriterStep () {
      return stepBuilderFactory
              .get ( "flatFileReaderWriterStep" )
              .<Employee, EmployeeInfo>chunk ( 10 )
              .reader ( csvReader )
              .processor ( employeeProcessor )
              .listener ( writerListener )
              .listener ( readerListener )
              .writer ( csvWriter )
              .build ();
   }

}
