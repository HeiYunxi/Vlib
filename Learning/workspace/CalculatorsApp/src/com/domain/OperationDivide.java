package com.domain;
//除法
public class OperationDivide extends Operation {
 @Override
 public double getResult() throws Exception {
     if (this.getNumB() == 0) {
         throw new Exception("Cannot divide by zero");
     }
     return this.getNumA() / this.getNumB();
 }
}