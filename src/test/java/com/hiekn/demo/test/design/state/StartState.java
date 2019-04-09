package com.hiekn.demo.test.design.state;

/**
 * describe about this class
 *
 * @author: DingHao
 * @date: 2019/4/9 17:13
 */
public class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    public String toString(){
        return "Start State";
    }
}
