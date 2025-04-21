package com.domain;
//立方根
public class OperationCbrt extends Operation {
 @Override
 public double getResult() throws Exception {
     return Math.cbrt(this.getNumA());
 }
}
