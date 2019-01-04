package com.hiekn.demo.test.design.visitor;

/**
 * describe about this class
 *
 * @author DingHao
 * @date 2019/1/4 10:20
 */
public interface ComputerPartVisitor {
    void visit(Computer computer);
    void visit(Mouse mouse);
    void visit(Keyboard keyboard);
    void visit(Monitor monitor);
}
