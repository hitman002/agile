package com.agile.pay

import scala.beans.BeanProperty

class SalariedClassification(@BeanProperty var salary:Double) extends PaymentClassification {
  
  def calculatePay(pc: PayCheck): Double = {
    salary
  }
  
}