package com.domain;
//乘法
public class OperationMultiply extends Operation {
 @Override
 public double getResult() throws Exception {
     return this.getNumA() * this.getNumB();
 }
}