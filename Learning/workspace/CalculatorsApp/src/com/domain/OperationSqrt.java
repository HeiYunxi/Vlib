package com.domain;
//平方根
public class OperationSqrt extends Operation {
 @Override
 public double getResult() throws Exception {
     if (this.getNumA() < 0) {
         throw new Exception("Cannot compute the square root of a negative number");
     }
     return Math.sqrt(this.getNumA());
 }
}