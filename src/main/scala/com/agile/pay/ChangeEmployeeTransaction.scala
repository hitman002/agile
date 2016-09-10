package com.agile.pay

abstract class ChangeEmployeeTransaction(empId:Int) extends Transaction {
   def execute(): Unit = {
    val e = PayrollDatabase.getEmployee(empId)
    if( e!= null){
      Change(e)
    }
  }

  def Change(e: Employee);
}