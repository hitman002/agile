package com.agile.pay

import scala.collection.mutable.Map
import scala.collection.mutable.HashMap

object PayrollDatabase {
   
  val itsEmployees:Map[Int,Employee] = new HashMap()
  
  def getEmployee(id:Int) :Employee= {
    if(itsEmployees.contains(id)){
      itsEmployees(id)
    }else{
      null
    }
  }
  
  def addEmployee(empId:Int,e:Employee) = {
    itsEmployees += (empId -> e)
  }

  def deleteEmployee(empId: Int) = {
    itsEmployees -= empId
  }
  
}