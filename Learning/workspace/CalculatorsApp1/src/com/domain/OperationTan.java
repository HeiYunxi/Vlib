package com.domain;
//三角函数 - 正切
public class OperationTan extends Operation {
 @Override
 public double getResult() throws Exception {
     return Math.tan(this.getNumA());
 }
}