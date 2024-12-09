package com.example.playcardsfx.controller.menucontroller;


import com.example.playcardsfx.controller.utilities.MediaManager;
import com.example.playcardsfx.controller.utilities.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/*
Thằng này quản lí việc chọn game
 */
public class SelectGameController implements Initializable {
    @FXML
    private Button  baCayButton;
    @FXML
    private Button samLocButton;
    @FXML
    private VBox addOptions;
    @FXML
    private Button samLoc1Button;
    @FXML
    private Button samLoc2Button;


    @FXML
    private ImageView back;
    @FXML
    private Label labelSelect;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
    /* Nhấn nút ba cây thì chạy game ba cây*/
    @FXML
    public void playBaCay(ActionEvent event) throws IOException {
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mouse-click-sound-233951.mp3", 1);
        SceneManager.getInstance().setPrimaryStage((Stage)baCayButton.getScene().getWindow());
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/BaCayScene.fxml", "/com/example/playcardsfx/stylefile/BaCayGameStyle.css");
    }

    /* Nhấn nút sâm lốc thì chạy game sâm lốc*/
    @FXML
    public void playSamloc(ActionEvent event) throws IOException {
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mouse-click-sound-233951.mp3", 1);
        addOptions.setVisible(!addOptions.isVisible());
        addOptions.setManaged(!addOptions.isVisible());
    }
    @FXML
    public  void playSamLocBot(ActionEvent event) throws IOException{
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mouse-click-sound-233951.mp3", 1);
        SceneManager.getInstance().setPrimaryStage((Stage)samLocButton.getScene().getWindow());
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/SamLocBotScene.fxml", "/com/example/playcardsfx/stylefile/SamLocGameStyle.css");
    }
    @FXML
    public  void playSamLocReal(ActionEvent event) throws IOException{
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mouse-click-sound-233951.mp3", 1);
        SceneManager.getInstance().setPrimaryStage((Stage)samLocButton.getScene().getWindow());
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/SamLocScene.fxml", "/com/example/playcardsfx/stylefile/SamLocGameStyle.css");
    }
    /* Back về màn hình chính*/
    @FXML
    public void back(MouseEvent event)throws IOException{
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mouse-click-sound-233951.mp3", 1);
        SceneManager.getInstance().setPrimaryStage((Stage)back.getScene().getWindow());
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/StartMenuScene.fxml", "/com/example/playcardsfx/stylefile/Style.css");
    }



}
