package com.domain;
//减法
public class OperationSubtract extends Operation {
    @Override
    public double getResult() throws Exception {
        return this.getNumA() - this.getNumB();
    }
}