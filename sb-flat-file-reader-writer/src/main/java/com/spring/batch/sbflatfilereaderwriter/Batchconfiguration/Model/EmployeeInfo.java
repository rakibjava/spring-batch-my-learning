package com.spring.batch.sbflatfilereaderwriter.Batchconfiguration.Model;

public class EmployeeInfo {
   private String employeeId;
   private String firstName;
   private String lastName;
   private int age;
   private String email;

   public EmployeeInfo () {
      // no arg constructor
   }

   public EmployeeInfo (String employeeId, String firstName, String lastName, int age, String email) {
      this.employeeId = employeeId;
      this.firstName = firstName;
      this.lastName = lastName;
      this.age = age;
      this.email = email;
   }

   public String getEmployeeId () {
      return employeeId;
   }

   public void setEmployeeId (String employeeId) {
      this.employeeId = employeeId;
   }

   public String getFirstName () {
      return firstName;
   }

   public void setFirstName (String firstName) {
      this.firstName = firstName;
   }

   public String getLastName () {
      return lastName;
   }

   public void setLastName (String lastName) {
      this.lastName = lastName;
   }

   public int getAge () {
      return age;
   }

   public void setAge (int age) {
      this.age = age;
   }

   public String getEmail () {
      return email;
   }

   public void setEmail (String email) {
      this.email = email;
   }

   @Override
   public String toString () {
      return "EmployeeInfo{" +
              "employeeId='" + employeeId + '\'' +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", age=" + age +
              ", email='" + email + '\'' +
              '}';
   }
}
