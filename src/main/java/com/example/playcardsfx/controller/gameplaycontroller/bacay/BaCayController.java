package com.example.playcardsfx.controller.gameplaycontroller.bacay;

import com.example.playcardsfx.utilities.MediaManager;
import com.example.playcardsfx.model.Deck;
import com.example.playcardsfx.model.Player;
import com.example.playcardsfx.utilities.SceneManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class BaCayController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button resetButton;

    @FXML
    private ImageView homeButton;
    @FXML
    private ImageView hand11, hand12, hand13, hand21, hand22, hand23;


    @FXML
    private Label notifi, player1Name, player2Name;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        MediaManager.getInstance().playBackgroundMusic("/MusicSource/BackgroundMusic/puzzle-game-bright-casual-video-game-music-249202.mp3",0.1);
        Deck deck1 = new Deck(1);
        deck1.shuffle();

        notifi.setStyle("-fx-text-fill: white;");
        player1Name.setStyle("-fx-text-fill: white;");
        player2Name.setStyle("-fx-text-fill: white;");

        Player Player1 = new Player("Player1");
        Player Player2 = new Player("Player2");

        //chia bài
        for(int i = 0; i < 6; i++){
            if(i % 2 == 0){
                Player1.addCard(deck1.dealCard());
            }
            else{
                Player2.addCard(deck1.dealCard());
            }
        }

        hand11.setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + Player1.getCardsOfPlayer().get(0).getRank() + Player1.getCardsOfPlayer().get(0).getSuit()+ ".png")));
        hand12.setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + Player1.getCardsOfPlayer().get(1).getRank() + Player1.getCardsOfPlayer().get(1).getSuit()+ ".png")));
        hand13.setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + Player1.getCardsOfPlayer().get(2).getRank() + Player1.getCardsOfPlayer().get(2).getSuit()+ ".png")));
        hand21.setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + Player2.getCardsOfPlayer().get(0).getRank() + Player2.getCardsOfPlayer().get(0).getSuit()+ ".png")));
        hand22.setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + Player2.getCardsOfPlayer().get(1).getRank() + Player2.getCardsOfPlayer().get(1).getSuit()+ ".png")));
        hand23.setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + Player2.getCardsOfPlayer().get(2).getRank() + Player2.getCardsOfPlayer().get(2).getSuit()+ ".png")));

        //Test LogicGame

        /*card11.setText(Player1.getCardsOfPlayer().get(0).getCardIndex());
        card21.setText(Player1.getCardsOfPlayer().get(1).getCardIndex());
        card31.setText(Player1.getCardsOfPlayer().get(2).getCardIndex());

        card12.setText(Player2.getCardsOfPlayer().get(0).getCardIndex());
        card22.setText(Player2.getCardsOfPlayer().get(1).getCardIndex());
        card32.setText(Player2.getCardsOfPlayer().get(2).getCardIndex());


         */


        BaCayLogicHelper ply1 = new BaCayLogicHelper(Player1.getCardsOfPlayer());
        BaCayLogicHelper ply2 = new BaCayLogicHelper(Player2.getCardsOfPlayer());
        //Tính tổng giá trị bài

        int m = ply1.countOfCardsIndex(Player1.getCardsOfPlayer());
        int n = ply2.countOfCardsIndex(Player2.getCardsOfPlayer());


        if(m > n){
            notifi.setText("Player 1 wins");
        }
        else if(n > m){
            notifi.setText("Player 2 wins");
        }

        else notifi.setText("Draw");
    }

    @FXML
    public void reset(ActionEvent event) throws IOException {
        SceneManager.getInstance().setPrimaryStage((Stage)resetButton.getScene().getWindow());
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/BaCayScene.fxml", "/com/example/playcardsfx/stylefile/BaCayGameStyle.css");
    }

    public  void homeButtonClicked(MouseEvent event){
        SceneManager.getInstance().setPrimaryStage((Stage)homeButton.getScene().getWindow());
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mixkit-water-sci-fi-bleep-902.mp3", 0.7);
        MediaManager.getInstance().playBackgroundMusic("/MusicSource/BackgroundMusic/retro-gaming-271301.mp3",0.5);
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/StartMenuScene.fxml", "/com/example/playcardsfx/stylefile/Style.css");
    }
    }




