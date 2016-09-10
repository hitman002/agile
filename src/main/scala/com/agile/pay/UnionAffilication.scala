package com.agile.pay

import scala.beans.BeanProperty

class UnionAffilication(memberId:Int, charge:Double) {
  
  @BeanProperty
  var serviceCharge:ServiceCharge = null;

  def addServiceCharge(date: Long, charge: Double) = {
    serviceCharge = new ServiceCharge(date,charge);
  }
  
  
}