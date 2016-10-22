package com.agile.pay

import java.util.Date

class PaydayTransaction(payDate:Date ) extends Transaction {
  
  var payChecks:Map[Int,PayCheck] = Map.empty;
  
  def execute(): Unit = {
    val emps = PayrollDatabase.getAllEmployee();
    emps.foreach { x => 
      if(x.isPayDate(payDate)){
        val pay = new PayCheck(x.getPayPeriodStartDate(payDate),payDate); 
        payChecks += (x.getEmpId() -> pay) ;
        x.payDay(pay) 
      } 
    }
  }

  def getPaycheck(empId: Int):PayCheck = {
    if(payChecks.contains(empId)){
    	payChecks(empId)
    }else{
    	null
    }
  }
}