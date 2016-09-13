package com.agile.pay

class AddCommissionedEmployee(empId:Int, name:String, address:String, salary:Double, commissionRate:Double) extends AddEmployeeTransaction(empId,name,address){

  def getClassification: PaymentClassification = {
    new CommissionedClassification(salary,commissionRate) 
  }

  def getSchedule: PaymentSchedule = {
    new WeeklySchedule
  }
}