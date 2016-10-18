package com.agile.pay

abstract class ChangeAffiliationTransaction(empId:Int) extends ChangeEmployeeTransaction(empId) {
  
  def recordMemberShip(e:Employee);
  
  def Change(e: Employee){
    recordMemberShip(e)
    
    e.setAffiliation(getAffiliation());
  }

  def getAffiliation(): Affiliation ;
  
}