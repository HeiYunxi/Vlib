package com.domain;
//取余
public class OperationModulus extends Operation {
 @Override
 public double getResult() throws Exception {
     return this.getNumA() % this.getNumB();
 }
}