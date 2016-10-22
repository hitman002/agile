package com.agile.pay

import java.util.Date

abstract class PaymentSchedule {
  def isPayDate(payDate: Date):Boolean = {
    true
  }

  def getPayPeriodStartDate(payDate: Date):Date;
}