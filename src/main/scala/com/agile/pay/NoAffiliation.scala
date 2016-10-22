package com.agile.pay

class NoAffiliation extends Affiliation {
  def calculateDeductions(pc: PayCheck): Double = {
    0
  }
}