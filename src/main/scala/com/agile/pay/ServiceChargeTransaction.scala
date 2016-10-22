package com.agile.pay

import java.util.Date

class ServiceChargeTransaction(memberId:Int, date:Date, charge:Double) extends Transaction {
  def execute(): Unit = {
    val e = PayrollDatabase.getUnionMember(memberId)
    val af = e.getAffiliation()
    if(af.isInstanceOf[UnionAffilication]){
      val uaf = af.asInstanceOf[UnionAffilication]
      uaf.addServiceCharge(date,charge)
    }
      
    
  }
}