package com.agile.pay

import scala.beans.BeanProperty

class HourlyClassification(@BeanProperty var timeCard:TimeCard,@BeanProperty var rate:Double) extends PaymentClassification {
  
  def this() = this(null,0d)
  
  def addTimeCard(timeCard:TimeCard) = {
    setTimeCard(timeCard)
  }
}