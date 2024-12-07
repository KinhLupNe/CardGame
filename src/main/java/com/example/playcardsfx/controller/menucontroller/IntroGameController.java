package com.example.playcardsfx.controller.menucontroller;

import com.example.playcardsfx.utilities.MediaManager;
import com.example.playcardsfx.utilities.SceneManager;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.ImageCursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;


import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class IntroGameController implements Initializable {
    @FXML
    StackPane stackPane;
    @FXML
    Label huongDan;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Tạo effect cho label
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), huongDan); // Thời gian 1 giây
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();


    }
    // Bắt sự kiện chuyển nhấn vô màn hình
    public void evenClickedScene(MouseEvent event){
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/click-menu-app-147357.mp3", 1);
        SceneManager.getInstance().switchScene(
                "/com/example/playcardsfx/fxmlfile/GameLoadingScene.fxml",
                "/com/example/playcardsfx/stylefile/Style.css");

    }


}
