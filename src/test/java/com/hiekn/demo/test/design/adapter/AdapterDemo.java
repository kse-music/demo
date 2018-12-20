package com.hiekn.demo.test.design.adapter;

/**
 * 适配器模式,可以统一两个不兼容的接口
 *
 */
public class AdapterDemo {

	public static void main(String[] args) {
		Player myPlayer = new MyPlayer();

		myPlayer.play("h.mp3");
		myPlayer.play("me.avi");
	}

}