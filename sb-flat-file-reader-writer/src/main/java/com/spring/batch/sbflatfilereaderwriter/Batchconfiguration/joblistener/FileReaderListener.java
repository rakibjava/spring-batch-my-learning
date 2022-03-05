package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.joblistener;

import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class FileReaderListener implements ItemReadListener<Employee> {
   private static final Logger log = LoggerFactory.getLogger ( FileReaderListener.class );
   int count=0;
   @Override
   public void beforeRead () {
      log.info ( "==start read==" + ++count);

   }

   @Override
   public void afterRead (Employee employee) {
      log.info ( "==end read ="+ employee );
   }

   @Override
   public void onReadError (Exception e) {

   }
}
