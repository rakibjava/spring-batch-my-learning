package com.spring.batch.sbflatfilereaderwriter;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SbFlatFileReaderWriterApplication {

   public static void main (String[] args) {
      SpringApplication.run ( SbFlatFileReaderWriterApplication.class, args );
   }

}
