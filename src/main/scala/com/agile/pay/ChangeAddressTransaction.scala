package com.agile.pay

class ChangeAddressTransaction(empId:Int, address:String) extends ChangeEmployeeTransaction(empId){
  
  def Change(e: Employee): Unit = {
    e.setAddress(address)
  }
  
}