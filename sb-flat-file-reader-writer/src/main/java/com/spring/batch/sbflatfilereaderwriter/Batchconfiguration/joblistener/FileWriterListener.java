package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.joblistener;

import com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model.EmployeeInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileWriterListener implements ItemWriteListener<EmployeeInfo> {
   private static final Logger log = LoggerFactory.getLogger ( FileWriterListener.class );

   @Override
   public void beforeWrite (List<? extends EmployeeInfo> list) {
      log.info ( "==start writing===" + list.size ());
      //log.info ( "---" + list);
   }

   @Override
   public void afterWrite (List<? extends EmployeeInfo> list) {
      log.info ( "==end writing==" +list.size ());
   }

   @Override
   public void onWriteError (Exception e, List<? extends EmployeeInfo> list) {
      log.info ( "==writing=="+ list);
   }

}
