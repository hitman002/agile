package com.agile.pay

class AddCommissionedEmployee(empId:Int, name:String, address:String, salary:Double, change:Double) extends AddEmployeeTransaction(empId,name,address){

  def getClassification: PaymentClassification = {
      ???
  }

  def getSchedule: PaymentSchedule = {
    ???
  }
}