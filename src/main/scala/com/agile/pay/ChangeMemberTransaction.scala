package com.agile.pay

class ChangeMemberTransaction(empId:Int, memberId:Int, money:Double) extends ChangeAffiliationTransaction(empId) {  
  
  def getAffiliation(): Affiliation = {
     new UnionAffilication(memberId,money);  
  }

  def recordMemberShip(e: Employee): Unit = {
     PayrollDatabase.addUnionMember(memberId, e);
  }
  
}