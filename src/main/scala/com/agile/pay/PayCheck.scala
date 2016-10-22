package com.agile.pay

import java.util.Date
import scala.beans.BeanProperty

class PayCheck(@BeanProperty var payPeroidStartDate:Date,@BeanProperty var payPeriodEndDate:Date) {
  
  @BeanProperty var grossPay:Double = 0;
  @BeanProperty var deductions:Double = 0;
  @BeanProperty var netPay:Double = 0;
  
  def getField(field:String):String = {
    ???
  }

}