package player;

import java.util.LinkedList;
import java.util.List;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;

public class Startup {
	
	public static List<Media> playlist;

	public static void start(){
		playlist = new LinkedList<Media>();
		JFXPanel panel = new JFXPanel();

		Decrypter decrypt = new Decrypter();
		
		
		System.out.println(playlist.size());
		Player player = new Player();
		
		player.play();
	}
}
