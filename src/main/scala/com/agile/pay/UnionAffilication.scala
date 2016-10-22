package com.agile.pay

import scala.beans.BeanProperty
import java.util.Date
import java.util.Calendar

class UnionAffilication(@BeanProperty var memberId:Int, @BeanProperty var dues:Double) extends Affiliation{
  @BeanProperty
  var serviceCharges:List[ServiceCharge] = List.empty;

  def addServiceCharge(date: Date, charge: Double) = {
    serviceCharges = serviceCharges.+:(new ServiceCharge(date,charge));
  }

  def calculateDeductions(pc: PayCheck): Double = {
    val fridays = numberOfFridaysInPayPeroid(pc.getPayPeroidStartDate(),pc.getPayPeriodEndDate())
    var serviceFee = 0.0 
    serviceCharges.foreach { x => 
      if(DateUtils.isInPayPeriod(pc.getPayPeroidStartDate(),pc.getPayPeriodEndDate(),x.getDate())){
    	  serviceFee += x.getAmount()
      }
    }  
    serviceFee + dues * fridays
  }

  def numberOfFridaysInPayPeroid(payPeroidStartDate: Date, payPeroidEndDate: Date):Int = {
    var fridays = 0
    var thisDay = payPeroidStartDate
    while(thisDay.before(payPeroidEndDate) || thisDay.equals(payPeroidEndDate)){
      val cal = Calendar.getInstance;
      cal.setTime(thisDay)
      val day = cal.get(Calendar.DAY_OF_WEEK)
      if(day == 6) fridays += 1
      
      cal.add(Calendar.DAY_OF_YEAR,1);
      thisDay = cal.getTime
    }
    fridays
  }
  
}