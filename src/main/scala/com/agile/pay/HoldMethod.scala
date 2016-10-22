package com.agile.pay

class HoldMethod extends PaymentMethod {
  def pay(pc: PayCheck): Unit = {
    println("-----------Pay---------");
  }
}