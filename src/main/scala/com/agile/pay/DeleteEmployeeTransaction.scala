package com.agile.pay

class DeleteEmployeeTransaction(empId:Int) extends Transaction {
  def execute(): Unit = {
    PayrollDatabase.deleteEmployee(empId)
    
    
  }
}