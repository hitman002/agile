package com.agile.pay

class ChangeUnffiliatedTransaction(empId:Int) extends ChangeAffiliationTransaction(empId){
    
    def getAffiliation(): Affiliation = {
      new NoAffiliation()
    }

    def recordMemberShip(e: Employee): Unit = {
      val af = e.getAffiliation()
      if(af.isInstanceOf[UnionAffilication]){
        val uaf = af.asInstanceOf[UnionAffilication];
        PayrollDatabase.removeUnionMember(uaf.getMemberId())
      }
      
      
    }
}