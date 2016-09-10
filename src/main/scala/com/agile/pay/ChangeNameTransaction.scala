package com.agile.pay

class ChangeNameTransaction(empId:Int, name:String) extends ChangeEmployeeTransaction(empId) {

  def Change(e: Employee) = {
    e.setName(name)
  }
}