package com.domain;
//三角函数 - 正弦
public class OperationSin extends Operation {
 @Override
 public double getResult() throws Exception {
     return Math.sin(this.getNumA());
 }
}