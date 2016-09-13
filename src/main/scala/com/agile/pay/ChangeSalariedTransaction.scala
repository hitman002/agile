package com.agile.pay

class ChangeSalariedTransaction(empId:Int,salary:Double) extends ChangeClassificationTransaction(empId){ 
 
  def getClassification(): PaymentClassification = {
    new SalariedClassification(salary)
  }
  
  def getSchedule(): PaymentSchedule = {
    new MonthlySchedule
  }
 
}