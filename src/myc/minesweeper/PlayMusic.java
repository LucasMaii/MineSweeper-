package myc.minesweeper;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
 
public class PlayMusic {
	private int a=1;
	URL url = this.getClass().getResource("半夜的清风.wav");       //wav 音乐文件放在你自己新建项目的bin目录下，不懂的百度。mp3文件不能播放，所以要把MP3文件转换为wav
    AudioClip christmas = Applet.newAudioClip(url);

//	AudioClip christmas = loadSound("src/music/说好不哭.wav");
//	AudioClip christmas =PlayMusic.class.getResourceAsStream("/music/说好不哭.wav");
	public void play() {
		if((a%2)!=0)
		christmas.play();
		else {
			christmas.stop();
		}
		a++;
	}
	
	public static void main(String[] args) {
		
		new PlayMusic();
	}
}