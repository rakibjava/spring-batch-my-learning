package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.jobReaderwriter;

import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.Employee;
import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.EmployeeInfo;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

@Configuration
public class CsvFlatFileReadAndWrite {

   private final Resource outputResource = new FileSystemResource ( "output/employee_output.csv" );

   @Value ("classPath:/input/employees.csv")
   @StepScope
   private Resource inputResource;

   @Bean
   @StepScope
   public FlatFileItemReader<Employee> csvReader () {
      /*FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
      reader.setResource(inputResource);
      reader.setLineMapper(new DefaultLineMapper<Employee>() {{
         setLineTokenizer(new DelimitedLineTokenizer() {{
            setNames("employeeId", "firstName", "lastName", "email", "age");
            setDelimiter(",");
         }});
         setFieldSetMapper ( new EmployeeFileRowMapper () );
      }});
      return reader;*/

      DefaultLineMapper<Employee> defaultLineMapper = new DefaultLineMapper<> ();

      DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer ();
      delimitedLineTokenizer.setDelimiter ( "," );
      delimitedLineTokenizer.setNames ( "employeeId", "firstName", "lastName", "email", "age" );
      //delimitedLineTokenizer.setIncludedFields ( 0,1,2,3,4,5 );

      BeanWrapperFieldSetMapper<Employee> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<> ();
      beanWrapperFieldSetMapper.setTargetType ( Employee.class );

      defaultLineMapper.setLineTokenizer ( delimitedLineTokenizer );
      defaultLineMapper.setFieldSetMapper ( beanWrapperFieldSetMapper );

      return new FlatFileItemReaderBuilder<Employee> ()
              .name ( "readCSV" )
              .resource ( inputResource )
              .saveState ( false )
              .lineMapper ( defaultLineMapper )
              .build ();
   }

   @Bean
   public FlatFileItemWriter<EmployeeInfo> csvWriter () {

      /*FlatFileItemWriter<EmployeeInfo> writer = new FlatFileItemWriter<>();
      writer.setResource(outputResource);

      writer.setLineAggregator(new DelimitedLineAggregator<EmployeeInfo>() {
         {
            setFieldExtractor(new BeanWrapperFieldExtractor<EmployeeInfo>() {
               {
                  setNames(new String[]{"employeeId", "firstName", "lastName", "email", "age"});
               }
            });
         }
      });
      writer.setShouldDeleteIfExists(true);

      return writer;*/
      BeanWrapperFieldExtractor<EmployeeInfo> fieldExtractor = new BeanWrapperFieldExtractor<> ();
      fieldExtractor.setNames ( new String[] { "employeeId", "firstName", "lastName", "email", "age" } );


      DelimitedLineAggregator<EmployeeInfo> lineAggregator = new DelimitedLineAggregator<> ();
      lineAggregator.setDelimiter ( "-" );
      lineAggregator.setFieldExtractor ( fieldExtractor );

      return new FlatFileItemWriterBuilder<EmployeeInfo> ()
              .name ( "writeCSV" )
              .resource ( outputResource )
              .saveState ( false )
              .lineAggregator ( lineAggregator )
              .shouldDeleteIfExists ( true )
              .build ();
   }
}
