package myc.minesweeper;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.JApplet;
 
public class PlayMusic {
	private int a=1;
	URL url = this.getClass().getResource("��ҹ�����.wav");       //wav �����ļ��������Լ��½���Ŀ��binĿ¼�£������İٶȡ�mp3�ļ����ܲ��ţ�����Ҫ��MP3�ļ�ת��Ϊwav
    AudioClip christmas = Applet.newAudioClip(url);

//	AudioClip christmas = loadSound("src/music/˵�ò���.wav");
//	AudioClip christmas =PlayMusic.class.getResourceAsStream("/music/˵�ò���.wav");
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