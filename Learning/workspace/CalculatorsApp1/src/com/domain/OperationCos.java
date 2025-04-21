package com.domain;
//三角函数 - 余弦
public class OperationCos extends Operation {
 @Override
 public double getResult() throws Exception {
     return Math.cos(this.getNumA());
 }
}