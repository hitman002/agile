package com.agile.pay

class AddComissonedEmployee(id: Int, name :String, address:String,salary:Double,commissionRate:Double ) extends AddEmployeeTransaction(id,name,address){ 
  
  
  def getClassification: PaymentClassification = {
    new CommissionedClassification(salary,commissionRate)
  }

  def getSchedule: PaymentSchedule = {
    new BiweeklySchedule
  }
  
  
  
}