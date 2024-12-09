package com.example.playcardsfx;

import com.example.playcardsfx.utilities.MediaManager;
import com.example.playcardsfx.utilities.SceneManager;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.ImageCursor;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        MediaManager.getInstance().playBackgroundMusic("/MusicSource/BackgroundMusic/retro-gaming-271301.mp3",0.5);
        SceneManager.getInstance().setPrimaryStage(stage);
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/IntroGameScene.fxml", "/com/example/playcardsfx/stylefile/IntroGameStyle.css");
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/ImageSource/ImageDecorateImgae/images.jpg")));
        stage.setTitle("Bài Bạc 88");
    }
    public static void main(String[] args) {
        launch();
    }
}