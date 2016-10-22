package com.agile.pay

import scala.beans.BeanProperty
import java.util.Date

class HourlyClassification(@BeanProperty var rate:Double) extends PaymentClassification {
  
  var timeCards:List[TimeCard] = List.empty;
  
  def this() = this(0d)
  
  def addTimeCard(timeCard:TimeCard) = {
    timeCards = timeCards.+:(timeCard)
  }
  
  def getTimeCard() = {
    timeCards(0)
  }
  
  def calculatePay(pc: PayCheck): Double = {
    var money = 0.0; 
    timeCards.foreach { x => 
      if(isInPayPeriod(pc,x.getDate())){
        money += calculatePayForTimeCard(x)
      }
    }
    money
  }
  
  def calculatePayForTimeCard(tc:TimeCard):Double = {
     val overtime = Math.max(0.0,tc.getHours() - 8.0) 
     val straightTime = tc.getHours() - overtime
     straightTime * rate + overtime * rate * 1.5
  }
  
}