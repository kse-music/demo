package com.hiekn.demo.test.design.visitor;

/**
 * describe about this class
 *
 * @author DingHao
 * @date 2019/1/4 10:19
 */
public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
