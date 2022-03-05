package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.jobrepository;

import org.springframework.boot.autoconfigure.batch.BatchDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class BatchMetaDataSourceConfig {

   @Bean
   @BatchDataSource
   DataSource batchDataSource () { //Datasource to use for job repository to store job and step metadata
      return DataSourceBuilder.create ()
              .url ( "jdbc:h2:~/test" )
              .username ( "sa" )
              .password ( "" )
              .driverClassName ( "org.h2.Driver" )
              .build ();
   }

}
