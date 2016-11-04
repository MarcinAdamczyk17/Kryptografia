package player;

import java.io.File;

import javafx.application.Application;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Test extends Application{

	public static void main(String[] args) {
		launch(args);
		
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Group root = new Group();
        Scene scene = new Scene(root, 500, 200);
		
		JFXPanel fxPanel = new JFXPanel();
		
		Media file = new Media("file:/D:/Programming/Kryptografia/Kryptografia/Player/my.mp3");
		
		MediaPlayer player = new MediaPlayer(file);

		player.play();
		
		MediaView mediaView = new MediaView(player);
        ((Group)scene.getRoot()).getChildren().add(mediaView);

        //show the stage
        primaryStage.setTitle("Media Player");
        primaryStage.setScene(scene);
        primaryStage.show();
		
	}

}
