//: Playground - noun: a place where people can play

import UIKit

struct CalculatorBrain {

    private var accumulator: Double?
    
    private typealias returnDouble = (Double) -> Double
    private typealias returnBinaryOp = (Double, Double) -> Double
    
    private enum Operation {
        
        case constant(Double) // <- Associated Value
        case unaryOperation(returnDouble)
        case binaryOperation(returnBinaryOp)
        case equals
    }
    
    
    private var operations: [String:Operation] = [
    
        "pie" : Operation.constant(Double.pi),
        "e"   : Operation.constant(M_E),
        "∆"   : Operation.unaryOperation(sqrt),
        "cos" : Operation.unaryOperation(cos),
        "+"   : Operation.binaryOperation( { $0 + $1 } )
    ]
    
    mutating func performOperation(_ symbol: String) {
        
        if let operation = operations[symbol] {
            
            switch operation {
                
            case .constant(let value):
                accumulator = value
                
            case .unaryOperation(let function):
                if accumulator != nil {
                    accumulator = function(accumulator!)
                }
            case .binaryOperation(let function):
                
                if accumulator != nil {
                    pendingBinaryOperation = PendingBinaryOperations(function: function, firstOperand: accumulator!)
                }
            case .equals:
                performPendingPinaryOperation()
            }
        }
    }
    
    private mutating func performPendingPinaryOperation() {
        if pendingBinaryOperation != nil && accumulator != nil {
            accumulator = pendingBinaryOperation?.perform(with: accumulator!)
            pendingBinaryOperation = nil
        }
    }
    
    private var pendingBinaryOperation: PendingBinaryOperations?
    
    private struct PendingBinaryOperations {
        
        let function: (Double, Double) -> Double
        let firstOperand: Double
    
        func perform(with secondOperand: Double) -> Double {
            
            return function(firstOperand, secondOperand)
        }
    }
    
    mutating func setOperand(_ operand: Double) {
        accumulator = operand
    }
    
    var result: Double? {
        get {
            return accumulator
        }
    }
}