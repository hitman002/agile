package com.agile.pay

import scala.beans.BeanProperty
import java.util.Date


class Employee(@BeanProperty var empId:Int, @BeanProperty var name : String, @BeanProperty var address:String) {
  @BeanProperty
  var classification : PaymentClassification = null
  @BeanProperty
  var schedule:PaymentSchedule = null
  @BeanProperty
  var method:PaymentMethod = null
  @BeanProperty
  var affiliation:Affiliation = new NoAffiliation()

  def isPayDate(payDate: Date) = {
    schedule.isPayDate(payDate)
  }

  def payDay(pc: PayCheck) = {
    val grossPay = classification.calculatePay(pc)
    val deductions = affiliation.calculateDeductions(pc)
    val netPay = grossPay - deductions;
    pc.setGrossPay(grossPay);
    pc.setDeductions(deductions);
    pc.setNetPay(netPay);
    
    method.pay(pc)
  }

  def getPayPeriodStartDate(payDate: Date):Date = {
    schedule.getPayPeriodStartDate(payDate)
  }
  
  
  
}