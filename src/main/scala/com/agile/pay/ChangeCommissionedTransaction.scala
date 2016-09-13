package com.agile.pay

class ChangeCommissionedTransaction(empId:Int,salary:Double,commissionRate:Double) extends ChangeClassificationTransaction(empId){

  def getClassification(): PaymentClassification = {
    new CommissionedClassification(salary,commissionRate)
  }

  def getSchedule(): PaymentSchedule = {
    new BlweeklySchedule
  }
  
  
}