package com.agile.pay

import scala.beans.BeanProperty

class AddSalariedEmployee(id: Int, name :String, address:String, salary: Double ) extends AddEmployeeTransaction(id,name,address) {
  
  override def toString = id +" "+ name + " " + address +" "+ salary+" "

  def getClassification: PaymentClassification = {
    return new SalariedClassification(salary)
  }

  def getSchedule: PaymentSchedule = {
    return new MonthlySchedule()
  }
  
  
  
}