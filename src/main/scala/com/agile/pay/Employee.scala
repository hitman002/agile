package com.agile.pay

class Employee(name : String) {
  
  val classification : PaymentClassification = null
  val schedule:PaymentSchedule = null
  val method:PaymentMethod = null
  
  def getName :String = {
    name
  }
  
  def getClassification :PaymentClassification  = {
    classification
  }
  
  def getSchedule :PaymentSchedule ={
    schedule
  }
  
  def getMethod : PaymentMethod={
    method
  }
  
  
}