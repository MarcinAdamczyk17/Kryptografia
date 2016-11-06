package player;

import java.util.List;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Player extends Application{
	int i = 0;
	public static List<Media> playlist;
	
	public Player(){
		this.playlist = Startup.playlist;;
	}
	
	public void play(){		
		launch();
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {

		if(i >= playlist.size())
			return;
		
		Group root = new Group();
        Scene scene = new Scene(root, 500, 200);
		scene.setFill(Paint.valueOf("#170495"));

		MediaPlayer player = new MediaPlayer(playlist.get(i));

		
		player.play();
		player.setOnEndOfMedia(new Runnable(){

			@Override
			public void run() {
				++i;
				try {
					start(primaryStage);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});

		MediaView mediaView = new MediaView(player);
        ((Group)scene.getRoot()).getChildren().add(mediaView);

        //show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();   
		
	}
}
