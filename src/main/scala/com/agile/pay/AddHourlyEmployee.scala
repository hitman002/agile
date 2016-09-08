package com.agile.pay

class AddHourlyEmployee(id: Int, name: String, address: String, hourlyRate: Double) extends AddEmployeeTransaction(id, name, address) {
  
  def getClassification: PaymentClassification = {
    new HourlyClassification
  }

  def getSchedule: PaymentSchedule = {
    new WeeklySchedule 
  }
}