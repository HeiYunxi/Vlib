package com.domain;

public class OperationAdd extends Operation {
 
    @Override
    public double getResult() throws Exception {
        return this.getNumA() + this.getNumB();
    }
}