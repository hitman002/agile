package com.agile.pay

import scala.beans.BeanProperty


class Employee(empId:Int,name : String,address:String) {
  @BeanProperty
  var classification : PaymentClassification = null
  @BeanProperty
  var schedule:PaymentSchedule = null
  @BeanProperty
  var method:PaymentMethod = null
  
  
  
}