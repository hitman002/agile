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
  
  @Test
  def testAddServiceCharge(){
    val empId = 2;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.25)
    t.execute
    
    val e = PayrollDatabase.getEmployee(empId)
    Assert.assertNotNull(e)
    
    val memberId = 25
    val af = new UnionAffilication(memberId,12.5)
    
    e.setAffiliation(af)
    PayrollDatabase.addUnionMember(memberId,e)
    
    val sct = new ServiceChargeTransaction(memberId,20011101,12.95) 
    sct.execute()
    
    val sc:ServiceCharge = af.getServiceCharge();  
    Assert.assertNotNull(sc);
    Assert.assertTrue(12.95d == sc.getAmount())
    
  }
  
  @Test
  def testChangeNameTransaction(){
    val empId = 2;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.5)
    t.execute
    
    val cnt = new ChangeNameTransaction(empId,"Bob");
    cnt.execute
    
    val e = PayrollDatabase.getEmployee(empId)
    Assert.assertNotNull(e)
    
    Assert.assertEquals(e.getName(),"Bob")
    
  }
  
  @Test def testChangeHourlyTransaction(){
    val empId = 3;
    val t = new AddCommissionedEmployee(empId,"Lance","Home",2500,3.2)
    t.execute
    
    val cht = new ChangeHourlyTransaction(empId,27.52)
    cht.execute
    
    val e = PayrollDatabase.getEmployee(empId)
    Assert.assertNotNull(e)
    
    val pc = e.getClassification()
    Assert.assertNotNull(pc)
    
    val hc = pc.asInstanceOf[HourlyClassification]
    Assert.assertNotNull(hc)
    
    Assert.assertEquals(27.52,hc.getRate());
    val ps = e.getSchedule()
    val ws = ps.asInstanceOf[WeeklySchedule]
    Assert.assertNotNull(ws)
    
  }
  
  
  
}