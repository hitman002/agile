package com.agile.pay

import java.util.Date

object DateUtils {
  
   def isInPayPeriod(startDate:Date,endDate:Date,thisDay:Date):Boolean = {
    (thisDay.after(startDate) || thisDay.equals(startDate) ) && 
    (thisDay.before(endDate) || thisDay.equals(endDate) )
  }
  
}