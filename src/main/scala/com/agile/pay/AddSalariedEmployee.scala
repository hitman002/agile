package com.agile.pay

class AddSalariedEmployee(id: Int, name :String, test:String, salary: Double ) extends Transaction {
  
  override def toString = id +" "+ name + " " + test +" "+ salary+" "

  def execute(): Unit = {
    ???
  }
  
}