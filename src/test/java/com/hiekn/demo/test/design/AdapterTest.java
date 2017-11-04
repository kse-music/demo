package com.hiekn.demo.test.design;

/**
 * 适配器模式,可以统一两个不兼容的接口
 * @author DH
 *
 */
public class AdapterTest {

	public static void main(String[] args) {
		Player myPlayer = new MyPlayer();

		myPlayer.play("h.mp3");
		myPlayer.play("me.avi");
	}

}

interface Player {
	void play(String fileName);
}

interface AudioPlayer {  
	void playAudio(String fileName);
}

interface VideoPlayer {  
	void playVideo(String fileName);
}

class MyAudioPlayer implements AudioPlayer {
	@Override
	public void playAudio(String fileName) {
		System.out.println("Playing. Name: "+ fileName);    
	}
}
class MyVideoPlayer implements VideoPlayer {
	@Override
	public void playVideo(String fileName) {
		System.out.println("Playing. Name: "+ fileName);    
	}
}

class MyPlayer implements Player {

	AudioPlayer audioPlayer = new MyAudioPlayer();
	VideoPlayer videoPlayer = new MyVideoPlayer();

	public MyPlayer(){  

	}

	@Override
	public void play(String fileName) {
		String audioType = fileName.substring(fileName.lastIndexOf(".")+1);
		if(audioType.equalsIgnoreCase("avi")){
			videoPlayer.playVideo(fileName);
		}else if(audioType.equalsIgnoreCase("mp3")){
			audioPlayer.playAudio(fileName);
		}else{
			System.out.println("无法播放");
		}
	}
}