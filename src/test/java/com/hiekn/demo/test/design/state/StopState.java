package com.hiekn.demo.test.design.state;

/**
 * describe about this class
 *
 * @author: DingHao
 * @date: 2019/4/9 17:14
 */
public class StopState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    public String toString(){
        return "Stop State";
    }
}