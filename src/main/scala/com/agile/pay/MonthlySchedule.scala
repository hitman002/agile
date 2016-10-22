package com.agile.pay

import java.util.Date
import java.util.Calendar

class MonthlySchedule extends PaymentSchedule {
  
  override def isPayDate(payDate: Date): Boolean = {
    val pay = Calendar.getInstance
    pay.setTime(payDate)
    
    val cal = Calendar.getInstance
    cal.setTime(payDate)
    cal.add(Calendar.MONTH,1)
    cal.set(Calendar.DAY_OF_MONTH,1)
    cal.add(Calendar.DAY_OF_YEAR,-1)
    
    pay.get(Calendar.DAY_OF_YEAR) == cal.get(Calendar.DAY_OF_YEAR)
  }

  def getPayPeriodStartDate(payDate: Date): Date = {
    val cal =  Calendar.getInstance
    cal.setTime(payDate)
    cal.set(Calendar.DAY_OF_MONTH,1)
    cal.getTime
  }
  
}