package com.agile.pay


import org.junit.Assert
import org.junit.Test
import java.util.Date


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
    
    val time = new TimeCardTransaction(new Date(),8.0d,empId);
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
    
    val sct = new ServiceChargeTransaction(memberId,new Date(),12.95) 
    sct.execute()
    
    val sc:ServiceCharge = af.getServiceCharges()(0)
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
    
    var pc = e.getClassification()
    Assert.assertNotNull(pc)
    
    val hc = pc.asInstanceOf[HourlyClassification]
    Assert.assertNotNull(hc)
    
    Assert.assertTrue(27.52 == hc.getRate());
    var ps = e.getSchedule()
    val ws = ps.asInstanceOf[WeeklySchedule]
    Assert.assertNotNull(ws)
    
    
    val cst = new ChangeSalariedTransaction(empId,4000)
    cst.execute
    
    pc = e.getClassification();
    
    val sc = pc.asInstanceOf[SalariedClassification]
    Assert.assertTrue(4000 == sc.getSalary());
    
    ps = e.getSchedule()
    val ms = ps.asInstanceOf[MonthlySchedule]
    Assert.assertNotNull(ms)

  }
  
  @Test def testChangeMemberTransaction(){
    val empId = 2;
    val memberId = 7734;
    
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.25);
    t.execute
    
    val cmt = new ChangeMemberTransaction(empId,memberId,99.42);
    cmt.execute
    
    val e = PayrollDatabase.getEmployee(empId);
    Assert.assertNotNull(e);
    
    val af = e.getAffiliation()
    Assert.assertNotNull(af);
    
    val uf = af.asInstanceOf[UnionAffilication]
    Assert.assertNotNull(uf);
    
    Assert.assertTrue(99.42==uf.getDues())
    
    val member = PayrollDatabase.getUnionMember(memberId);
    Assert.assertNotNull(member);
    Assert.assertEquals(e,member);
  
  }
  
  @Test def testPaySingleSalariedEmployee(){
    val empId = 1;
    val t = new AddSalariedEmployee(empId,"Bob","Home",1000.00);
    t.execute
    val payDate = new Date(116,9,30);
    val pt = new PaydayTransaction(payDate);
    pt.execute
    
    val pc:PayCheck = pt.getPaycheck(empId);
    Assert.assertNotNull(pc);
    Assert.assertTrue(1000.00 == pc.getGrossPay());
    Assert.assertEquals("Hold",pc.getField("Disposition"));
    Assert.assertTrue(0.0 == pc.getDeductions());
    Assert.assertTrue(1000.00 == pc.getNetPay());
    
  }
  
  @Test def testPaySingleSalariedEmployeeOnWrongDate(){
    val empId = 1;
    val t = new AddSalariedEmployee(empId,"Bob","Home",1000.00);
    t.execute
    val payDate = new Date(116,9,11);
    val pt = new PaydayTransaction(payDate);
    pt.execute()
    val pc = pt.getPaycheck(empId);
    Assert.assertNotNull(pc);
  }
  
  @Test def testPaySingleHourlyEmployeeNoTimeCards(){
    val empId = 2;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.25)
    t.execute
    val payDate = new Date(116,9,11)
    val pt = new PaydayTransaction(payDate);
    pt.execute
    validatePaycheck(pt,empId,payDate,0.0);
  }

  def validatePaycheck(pt: PaydayTransaction, empId: Int, payDate: Date, pay: Double) = {
    val pc = pt.getPaycheck(empId)
    Assert.assertNotNull(pc)
    Assert.assertEquals(pc.getPayPeriodEndDate(),payDate)
    println(pay)
    println(pc.getGrossPay())
    Assert.assertTrue(pay == pc.getGrossPay())
 //   Assert.assertEquals("Hold",pc.getField("Disposition"))
    Assert.assertTrue(pc.getDeductions()== 0.0)     
    Assert.assertTrue(pay == pc.getNetPay())
  }
    
   def validatePaycheck(pt: PaydayTransaction, empId: Int, payDate: Date, pay: Double,duc:Double) = {
    val pc = pt.getPaycheck(empId)
    Assert.assertNotNull(pc)
    Assert.assertEquals(pc.getPayPeriodEndDate(),payDate)
    println(pay)
    println(pc.getGrossPay())
    Assert.assertTrue(pay == pc.getGrossPay())
 //   Assert.assertEquals("Hold",pc.getField("Disposition"))
    println(pc.getDeductions())
    println(duc)
    Assert.assertTrue(pc.getDeductions()== duc)     
    Assert.assertTrue(pay == pc.getNetPay() + duc)
  }
  
  
  @Test def testPaySingleHourlyEmployeeOneTimeCard(){
    val empId = 2;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.25);
    t.execute
    val payDate = new Date(116,9,11);
    
    val tc = new TimeCardTransaction(payDate,2.0,empId);
    tc.execute
    
    val pt = new PaydayTransaction(payDate);
    pt.execute
    
    validatePaycheck(pt, empId, payDate, 30.5);
  }
  
  @Test def testPaySingleHourlyEmployeeOvertimeOneTimeCard(){
    val empId = 2;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.25)
    t.execute
    
    val payDate = new Date(116,9,21)
    val tc = new TimeCardTransaction(payDate,9.5,empId);
    tc.execute
    
    val pt = new PaydayTransaction(payDate)
    pt.execute
    
    validatePaycheck(pt, empId, payDate,(8+1.5)*15.25);
  }
 
  
  @Test def testPaySingleHourlyEmployeeOnWrongDate(){
    val empId = 2;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.25)
    t.execute
    
    val payDate = new Date(116,9,20);
    val tc = new TimeCardTransaction(payDate,9.0,empId)
    tc.execute
    
    val pt = new PaydayTransaction(payDate)
    pt.execute
    
    val pc = pt.getPaycheck(empId)
    Assert.assertNull(pc)
    
  }
  
  @Test def testPaySingleHourlyEmployeeTwoTimeCards(){
    val empId = 2;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.25)
    t.execute
    
    val payDate = new Date(116,9,21);
    val tc = new TimeCardTransaction(payDate,2.0,empId)
    tc.execute
    
    val tc2 = new TimeCardTransaction(new Date(116,9,20),5.0,empId)
    tc2.execute
    
    val pt = new PaydayTransaction(payDate)
    pt.execute
    
    validatePaycheck(pt, empId, payDate, 7*15.25)
    
  }
  
  
  @Test def testPaySingleHourlyEmployeeWithTimeCardsSpanningTwoPayPeriods(){
    
    val empId = 2;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.25)
    t.execute
    
    val payDate = new Date(116,9,21)
    val dateInPreviousPayPeriod = new Date(116,8,21)
    
    val tc = new TimeCardTransaction(payDate,2.0,empId)
    tc.execute
    
    val tc2 = new TimeCardTransaction(dateInPreviousPayPeriod,5.0,empId)
    tc2.execute
    
    val pt = new PaydayTransaction(payDate)
    pt.execute
    
    validatePaycheck(pt, empId, payDate,2*15.25)
    
  }
  
  @Test def testSalariedUnionMemberDues(){
    val empId = 1;
    val t = new AddSalariedEmployee(empId,"Bob","Home",1000.00)
    t.execute
    
    val memberId = 7734;
    val cmd = new ChangeMemberTransaction(empId,memberId,9.42)
    cmd.execute
    
    val payDate = new Date(116,10,0)
    val pt = new PaydayTransaction(payDate)
    pt.execute
    
    validatePaycheck(pt, empId, payDate,1000.00,9.42*4 );
    
  }
  
  @Test def testHourlyUnionMemberServiceCharge(){
    val empId = 1;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.24)
    t.execute
    
    val memberId = 7734
    val cmt = new ChangeMemberTransaction(empId,memberId,9.42)
    cmt.execute
    
    val payDate = new Date(116,9,21)
    val sct = new ServiceChargeTransaction(memberId,payDate,19.42)
    sct.execute
    
    val tct = new TimeCardTransaction(payDate,8.0,empId)
    tct.execute
    
    val pt = new PaydayTransaction(payDate)
    pt.execute
    
    validatePaycheck(pt, empId, payDate,8*15.24,9.42 + 19.42 );
    
  }
  
  @Test def testServiceChargesSpanningMultiplePayPeriods(){
    val empId = 1;
    val t = new AddHourlyEmployee(empId,"Bill","Home",15.24)
    t.execute
    
    val memberId = 7734
    val cmt = new ChangeMemberTransaction(empId,memberId,9.42)
    cmt.execute
    
    val earlyDate = new Date(101,10,2)
    val payDate = new Date(101,10,9)
    val lateDate = new Date(101,10,16)
    
    val sct = new ServiceChargeTransaction(memberId,payDate,19.42)
    sct.execute
    
    val sctEarly = new ServiceChargeTransaction(memberId,earlyDate,100)
    sctEarly.execute
    
    val sctLate = new ServiceChargeTransaction(memberId,lateDate,200)
    sctLate.execute
    
    val tct = new TimeCardTransaction(payDate,8.0,empId)
    tct.execute
    
    val pt = new PaydayTransaction(payDate)
    pt.execute
    
    val pc = pt.getPaycheck(empId)
    
    validatePaycheck(pt, empId, payDate,8*15.24,9.42+19.42);
    
  }
  
  
  
  
  
  
  
}