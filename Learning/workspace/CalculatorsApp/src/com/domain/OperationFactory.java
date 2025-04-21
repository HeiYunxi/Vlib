package com.domain;
//操作工厂类
public class OperationFactory {
 public static Operation createOperation(String operation) {
     switch (operation) {
         case "+":
             return new OperationAdd();
         case "-":
             return new OperationSubtract();
         case "*":
             return new OperationMultiply();
         case "/":
             return new OperationDivide();
         case "%":
             return new OperationModulus();
         case "sin":
             return new OperationSin();
         case "cos":
             return new OperationCos();
         case "tan":
             return new OperationTan();
         case "sqrt":
             return new OperationSqrt();
         case "cbrt":
             return new OperationCbrt();
         case "pow":
             return new OperationPower();
         default:
             throw new IllegalArgumentException("Unknown operation: " + operation);
     }
 }
}
