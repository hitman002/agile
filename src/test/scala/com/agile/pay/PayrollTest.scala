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
  
  @Test
  def testTimeCardTransaction() = {
    val empId = 2;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.25)
    
    t.execute
    
    val time = new TimeCardTransaction(9854123l,8.0d,empId);
    time.execute()
    
    val e = PayrollDatabase.getEmployee(empId)
    Assert.assertNotNull(e)
    
    val pc = e.getClassification()
    val hc = pc.asInstanceOf[HourlyClassification]
    Assert.assertNotNull(hc)
    
    val tc = hc.getTimeCard()
    Assert.assertNotNull(tc)
    Assert.assertTrue(8.0 - tc.getHours() == 0.0)
    
  }
  
  
  
}