package com.agile.pay

class AddHourlyEmployee(id: Int, name: String, address: String, hourlyRate: Double) extends AddEmployeeTransaction(id, name, address) {
  
  def getClassification: PaymentClassification = {
    new HourlyClassification(hourlyRate)
  }

  def getSchedule: PaymentSchedule = {
    new WeeklySchedule 
  }
}