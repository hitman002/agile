package com.agile.pay

import scala.beans.BeanProperty


class Employee(@BeanProperty var empId:Int,@BeanProperty var name : String,@BeanProperty var address:String) {
  @BeanProperty
  var classification : PaymentClassification = null
  @BeanProperty
  var schedule:PaymentSchedule = null
  @BeanProperty
  var method:PaymentMethod = null
  @BeanProperty
  var affiliation:UnionAffilication = null
}