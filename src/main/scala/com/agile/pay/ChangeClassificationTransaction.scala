package com.agile.pay

abstract class ChangeClassificationTransaction(empId:Int) extends ChangeEmployeeTransaction(empId:Int){
  def Change(e: Employee): Unit = {
    e.setClassification(getClassification())
    e.setSchedule(getSchedule())
  }

  def getClassification():PaymentClassification

  def getSchedule():PaymentSchedule
}