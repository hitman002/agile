package com.agile.pay

import java.util.Date
import java.util.Calendar

class WeeklySchedule extends PaymentSchedule{
   override def isPayDate(payDate: Date): Boolean = {
    val cal = Calendar.getInstance;
    cal.setTime(payDate)
    val day = cal.get(Calendar.DAY_OF_WEEK)
    day == 6 
  }
   
  override def getPayPeriodStartDate(payDate: Date):Date = {
    val endDate = new Date()
    endDate.setTime(payDate.getTime() - 1000*60*60*24*5)
    endDate
  } 
   
}