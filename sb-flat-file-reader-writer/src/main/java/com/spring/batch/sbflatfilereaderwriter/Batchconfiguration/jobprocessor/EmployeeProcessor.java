package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.jobprocessor;

import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.Employee;
import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.EmployeeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, EmployeeInfo> {

   private static final Logger LOGGER = LoggerFactory.getLogger ( EmployeeProcessor.class );

   @Override
   public EmployeeInfo process (Employee employee) throws Exception {
      EmployeeInfo employeeInfo = new EmployeeInfo ();
      employeeInfo.setEmployeeId ( employee.getEmployeeId () );
      employeeInfo.setFirstName ( employee.getFirstName () );
      employeeInfo.setLastName ( employee.getLastName () );
      employeeInfo.setEmail ( employee.getEmail () );
      employeeInfo.setAge ( employee.getAge () + 10 );

      //LOGGER.info ( "Processor: " + employeeInfo);

      return employeeInfo;
   }
}
