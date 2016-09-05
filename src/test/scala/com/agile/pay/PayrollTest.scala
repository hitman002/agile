package com.agile.pay

import org.junit.Assert


object PayrollTest {
  
  def testAddSalariedEmployee() = {
    val empId = 1
    var t =new AddSalariedEmployee(empId,"Bob","Home",1000.00)
    t.execute
    
    val e = PayrollDatabase.getEmployee(empId)
//    Assert.assertEquals("Bob",e.getName)    

    val pc = e.getClassification
    val sc = pc.asInstanceOf[SalariedClassification]
    Assert.assertNotNull(sc)
    
    Assert.assertEquals(1000.00,sc.getSalary())    
    val ps = e.getSchedule ;
    val ms = e.asInstanceOf[MonthlySchedule]
    Assert.assertNotNull(ms)
    
    val pm = e.getMethod
    val hm = e.asInstanceOf[HoldMethod]
    Assert.assertNotNull(hm)
    
  }
  
  
  def main(args: Array[String]): Unit = {
    PayrollTest.testAddSalariedEmployee()
  }
}