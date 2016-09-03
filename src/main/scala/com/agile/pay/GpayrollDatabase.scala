package com.agile.pay

import scala.collection.immutable.Map
import scala.collection.immutable.HashMap

object PayrollDatabase {
   
  val itsEmployees:Map[Int,Employee] = new HashMap()
  
  def getEmployee(id:Int) :Employee= {
    itsEmployees.apply(id)
  }
  
  def addEmployee(empId:Int,e:Employee) = {
    itsEmployees.updated(empId,e)
  }
  
}