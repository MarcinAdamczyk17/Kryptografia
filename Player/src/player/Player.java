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

	public static List<Media> playlist;
	
	public Player(){
		this.playlist = Startup.playlist;;
	}
	
	public void play(){		
		launch();
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		int i = 0;
		Group root = new Group();
        Scene scene = new Scene(root, 500, 200);
		scene.setFill(Paint.valueOf("#170495"));

		Media media = playlist.get(i);
		MediaPlayer player = new MediaPlayer(media);
		player.setStartTime(new Duration(100000));
		player.play();
		
		
		MediaView mediaView = new MediaView(player);
        ((Group)scene.getRoot()).getChildren().add(mediaView);

        //show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();   
		
	}
}
