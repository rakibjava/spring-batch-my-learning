package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.joblistener;

import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.Employee;
import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.EmployeeInfo;
import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

@Component
public class FileProcessorListener implements ItemProcessListener<Employee, EmployeeInfo> {
   @Override
   public void beforeProcess (Employee employee) {

   }

   @Override
   public void afterProcess (Employee employee, EmployeeInfo employeeInfo) {

   }

   @Override
   public void onProcessError (Employee employee, Exception e) {

   }
}
