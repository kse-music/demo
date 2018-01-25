package com.hiekn.demo.test.design.adapter;

public class MyPlayer implements Player {

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
