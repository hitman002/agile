package com.agile.pay

abstract class AddEmployeeTransaction(empId:Int,name:String,address:String) {
  
  def getClassification:PaymentClassification ;
  
  def getSchedule:PaymentSchedule;
  
  def execute: Unit = {
    val pc = getClassification
    val ps = getSchedule
    val pm = new HoldMethod
    val e = new Employee(empId,name,address)
    e.setClassification(pc)
    e.setSchedule(ps)
    e.setMethod(pm)
    
    PayrollDatabase.addEmployee(empId, e)
    
  }
  
  
  
  
}