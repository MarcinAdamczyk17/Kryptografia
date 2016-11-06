package player;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

public class Player extends Application{

	Media file;
	
	public Player(){
		this.file = Test.file;;
	}
	
	public void play(){		
		launch();
		
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group root = new Group();
        Scene scene = new Scene(root, 500, 200);
		scene.setFill(Paint.valueOf("#170495"));
		
		//Media file = new Media("file:/home/marcin/Programming/krypto/Kryptografia/Player/my.mp3");
		
		MediaPlayer player = new MediaPlayer(file);

		player.play();
		
		player.play();
		MediaView mediaView = new MediaView(player);
        ((Group)scene.getRoot()).getChildren().add(mediaView);

        //show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
        
        
        
        
        
		
	}
}
