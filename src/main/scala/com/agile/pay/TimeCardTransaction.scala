package com.agile.pay

class TimeCardTransaction(date:Long,hours:Double,empId:Int) extends Transaction {
  
  def execute(): Unit = {
    val e = PayrollDatabase.getEmployee(empId)
    if(e != null){
      val pc = e.getClassification()
      if(pc.isInstanceOf[HourlyClassification]){
        val hc = pc.asInstanceOf[HourlyClassification]
        hc.addTimeCard(new TimeCard(date,hours))
      }else{
        throw new RuntimeException("Tired to add timecard to non-hourly employee")
      }
    }else{
      throw new RuntimeException("No such employee")
    }
  }
}