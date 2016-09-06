package com.agile.pay

import org.junit.Assert
import org.junit.Test


class PayrollTest {
  
  @Test
  def testAddSalariedEmployee() = {
    val empId = 1
    var t =new AddSalariedEmployee(empId,"Bob","Home",1000.00)
    t.execute
    
    val e = PayrollDatabase.getEmployee(empId)
    Assert.assertEquals("Bob",e.getName())    

    val pc = e.getClassification
    val sc = pc.asInstanceOf[SalariedClassification]
    Assert.assertNotNull(sc)
    
    Assert.assertTrue(1000.00==sc.getSalary())    
    val ps = e.getSchedule ;
    val ms = ps.asInstanceOf[MonthlySchedule]
    Assert.assertNotNull(ms)
    
    val pm = e.getMethod
    val hm = pm.asInstanceOf[HoldMethod]
    Assert.assertNotNull(hm)
  }
  
  @Test
  def testDeleteEmployee() = {
    val empId = 3;
    val t = new AddComissonedEmployee(empId,"Lance","Home",2500,3.2)
    t.execute
    
    val e = PayrollDatabase.getEmployee(empId)
    Assert.assertNotNull(e)
    
    val d = new DeleteEmployeeTransaction(empId)
    d.execute
    
    val e2 = PayrollDatabase.getEmployee(empId)
    Assert.assertNull(e2)
    
    
  }
  
  
  
}