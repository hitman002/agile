package com.agile.pay

import java.util.Date

abstract class PaymentClassification {
  def calculatePay(pc: PayCheck):Double
  
   def isInPayPeriod(pc:PayCheck,payDate:Date):Boolean = {
    val payPeriodStartDate = pc.getPayPeroidStartDate()
    val payPeriodEndDate = pc.getPayPeriodEndDate()
    (payDate.after(payPeriodStartDate) || payDate.equals(payPeriodStartDate) ) && 
    (payDate.before(payPeriodEndDate) || payDate.equals(payPeriodEndDate) )
  }
}