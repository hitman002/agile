package com.agile.pay

import scala.beans.BeanProperty

class UnionAffilication(@BeanProperty var memberId:Int,@BeanProperty var dues:Double) extends Affiliation{
  
  @BeanProperty
  var serviceCharge:ServiceCharge = null;

  def addServiceCharge(date: Long, charge: Double) = {
    serviceCharge = new ServiceCharge(date,charge);
  }
  
  
}