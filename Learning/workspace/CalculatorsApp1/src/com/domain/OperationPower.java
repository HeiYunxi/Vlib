package com.domain;
//幂运算
public class OperationPower extends Operation {
 @Override
 public double getResult() throws Exception {
     return Math.pow(this.getNumA(), this.getNumB());
 }
}