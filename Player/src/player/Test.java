package player;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;

import javafx.scene.media.Media;

public class Test {

	public static Media file;
	
	public static void main(String[] args) {
		
		
		Decrypter decrypt = new Decrypter();
		file = new Media("file:/home/marcin/Programming/krypto/Kryptografia/Player/play/my.mp3");
		
		Player player = new Player();
		
		player.play();
		
		
	}

	

}
