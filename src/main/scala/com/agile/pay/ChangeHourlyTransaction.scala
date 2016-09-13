package com.agile.pay

class ChangeHourlyTransaction(empId:Int, hourlyRoute:Double) extends ChangeClassificationTransaction(empId) {
  
  def getClassification(): PaymentClassification = {
    new HourlyClassification(hourlyRoute)
  }

  def getSchedule(): PaymentSchedule = {
    new WeeklySchedule
  }
}