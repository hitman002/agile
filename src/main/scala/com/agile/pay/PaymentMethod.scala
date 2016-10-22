package com.agile.pay

import java.util.Date

abstract class PaymentMethod {
  def pay(pc: PayCheck);

}