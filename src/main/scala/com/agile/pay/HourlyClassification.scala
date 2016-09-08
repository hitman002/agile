package com.agile.pay

import scala.beans.BeanProperty

class HourlyClassification(@BeanProperty var timeCard:TimeCard) extends PaymentClassification {
  
  def this() = this(null)
  
  def addTimeCard(timeCard:TimeCard) = {
    setTimeCard(timeCard)
  }
}