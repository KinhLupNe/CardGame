package com.example.playcardsfx.controller.gameplaycontroller;

import com.example.playcardsfx.model.enities.Card;
import com.example.playcardsfx.model.enities.Deck;
import com.example.playcardsfx.model.enities.Player;
import com.example.playcardsfx.model.gamelogic.samloc.CardHelper;
import com.example.playcardsfx.model.gamelogic.samloc.CardRepresentative;
import com.example.playcardsfx.controller.utilities.MediaManager;
import com.example.playcardsfx.controller.utilities.SceneManager;
import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.ResourceBundle;

public class SamLocBotController implements Initializable {

    @FXML
    private ImageView hand11, hand12, hand13, hand14, hand15, hand16, hand17,hand18, hand19, hand110, hand21, hand22, hand23, hand24, hand25, hand26, hand27, hand28, hand29, hand210;
    @FXML
    private Label centerHand;
    @FXML
    private Button resetButton;
    @FXML
    private ImageView homeButton;
    @FXML
    private Label result;

    private HBox hBox;
    //lop nguoi choi
    private Player player, bot;

    private CardRepresentative currentHand;
    private ArrayList<Integer> idx1, idx2;

    //Hinh anh la bau
    private ArrayList<ImageView> handsOfPlayer, handsOfBot;
    private HashMap<ImageView, Boolean> cardStates;
    private ArrayList<Card> handOfPlayer, handOfBot;
    private CardHelper turn;
    private CardRepresentative check;
    private int checkTurn;
    private int k;
    private int m, n, z;
    private int length, number, type, length2, count;
    private int excepLen;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Khoi tao tro choi moi
        Deck deck3 = new Deck();
        deck3.shuffle();
        hBox = new HBox(10);
        hBox.setAlignment(Pos.CENTER);
        centerHand.setAlignment(Pos.CENTER);
        player = new Player("Player1");
        bot = new Player("Bot");
        m = 10;
        n = 10;
        //Tao ra hand bai hien tai o centerHand
        currentHand = new CardRepresentative(0, 0 ,0);
        for(int i = 0; i < 20; i++){
            if(i % 2 == 0){
                player.addCard(deck3.dealCard());
            }
            else{
                bot.addCard(deck3.dealCard());
            }
        }
        //Tao chi so cua cac la bai
        idx1 = new ArrayList<>();
        idx2 = new ArrayList<>();

        // Tao hand bai cua Bot
        handOfBot = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            Collections.addAll(handOfBot, bot.getCardOfPlayer(i));
        }

        handsOfPlayer = new ArrayList<>();
        Collections.addAll(handsOfPlayer, hand11, hand12, hand13, hand14, hand15, hand16, hand17,hand18, hand19, hand110);
        handsOfBot = new ArrayList<>();
        Collections.addAll(handsOfBot, hand21, hand22, hand23, hand24, hand25, hand26, hand27, hand28, hand29, hand210);

        for (int i = 0; i < 10; i++) {
            handsOfPlayer.get(i).setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + player.getCardsOfPlayer().get(i).getRank() + player.getCardsOfPlayer().get(i).getSuit() + ".png")));
            handsOfBot.get(i).setImage(new Image(getClass().getResourceAsStream("/ImageSource/CardsImage/" + bot.getCardsOfPlayer().get(i).getRank() + bot.getCardsOfPlayer().get(i).getSuit() + ".png")));
        }

        handOfPlayer = new ArrayList<>();
        cardStates = new HashMap<>();
        //Tao bien check luot di
        checkTurn = 0;
        turn = new CardHelper();
    }

    public void onCardClicked(MouseEvent mouseEvent) {
        // neu getSource ra kieu du lieu ImageView
        if (mouseEvent.getSource() instanceof ImageView) {
            ImageView clicked = (ImageView) mouseEvent.getSource();
            k = handsOfPlayer.indexOf(clicked);
            turn = new CardHelper();
            // Lấy trạng thái của ImageView (true = đang ở trên, false = đang ở dưới)
            boolean isMovedUp = cardStates.getOrDefault(clicked, false);
            //Tao hand bai hien tai
            // Tạo hiệu ứng di chuyển
            TranslateTransition transition = new TranslateTransition(Duration.millis(50), clicked);
            if (!isMovedUp) {
                transition.setToY(-20); // Di chuyển len tren
                handOfPlayer.add(player.getCardOfPlayer(k));
                idx1.add(k);
                cardStates.put(clicked, true);
            }
            else{
                transition.setToY(0);
                handOfPlayer.remove(player.getCardOfPlayer(k));
                idx1.remove(Integer.valueOf(k));
                cardStates.put(clicked, false);

            }
            check = turn.generateRepresentative(handOfPlayer);
            transition.play();
        }

    }



    public void danhBai(ActionEvent actionEvent) {
        turn = new CardHelper();
        if(currentHand.getLength() == 0 && currentHand.getNumber() == 0 && currentHand.getType() == 0 && checkTurn == 0) {
            check = turn.generateRepresentative(handOfPlayer);
            if (check.getLength() != -1) {
                currentHand = check;
                z = idx1.size();
                hBox.getChildren().clear();
                centerHand.setGraphic(null);

                for (int i = 0; i < z; i++) {
                    hBox.getChildren().add(handsOfPlayer.get(idx1.get(i)));
                    centerHand.setGraphic(hBox);
                }
                idx1.clear();
                handOfPlayer.clear();
                checkTurn = 1;
                m -= z;

                if (m == 0) {
                    win();
                }

            }
        }

        else if(checkTurn == 0) {
            length = turn.generateRepresentative(handOfPlayer).getLength();
            number = turn.generateRepresentative(handOfPlayer).getNumber();
            type = turn.generateRepresentative(handOfPlayer).getType();
            System.out.println(length + " " + number + " " + type);
            hBox.getChildren().clear();
            centerHand.setGraphic(null);
            if (currentHand.getLength() == length && currentHand.getNumber() < number && currentHand.getType() == type) {
                currentHand = check;

                z = idx1.size();

                for (int i = 0; i < z; i++) {
                    hBox.getChildren().add(handsOfPlayer.get(idx1.get(i)));
                    centerHand.setGraphic(hBox);
                }
                idx1.clear();
                handOfPlayer.clear();
                checkTurn = 1;
                m -= z;
                if (m == 0) {

                    win();
                }
            }
            else checkTurn = 1;
        }

        else if(currentHand.getLength() == 1 && currentHand.getNumber() == 15 && currentHand.getType() == 1){

            if(type == 4){
                currentHand = check;
                hBox.getChildren().clear();
                centerHand.setGraphic(null);

                m = idx1.size();
                for(int i = 0; i < m; i++){
                    hBox.getChildren().add(handsOfPlayer.get(idx1.get(i)));
                    centerHand.setGraphic(hBox);
                    handOfPlayer.remove(idx1.get(i));
                }
                idx1.clear();
                checkTurn = 1;
                m -= z;
                if(m == 0){
                    win();
                }
            }
        }

        if(checkTurn == 1){
            if(currentHand.getLength() == 1 && currentHand.getType() == 1){
                count = 0;
                length2 = currentHand.getNumber();
                System.out.println("Length2: " + length2);
                hBox.getChildren().clear();
                centerHand.setGraphic(null);
                for(int i = 0; i < handOfBot.size(); i++){
                    if(handOfBot.get(i).getRank().equals("J")){
                        excepLen = 11;
                    }
                    else if(handOfBot.get(i).getRank().equals("Q")){
                        excepLen = 12;
                    }
                    else if(handOfBot.get(i).getRank().equals("K")){
                        excepLen = 13;
                    }

                    else if(handOfBot.get(i).getRank().equals("A")){
                        excepLen = 14;
                    }
                    else if(handOfBot.get(i).getRank().equals("2")){
                        excepLen = 15;
                    }
                    else excepLen = Integer.parseInt(handOfBot.get(i).getRank());

                    System.out.println("ExcepLen: " + excepLen);
                    if(length2 < excepLen){
                        currentHand = new CardRepresentative(1, excepLen,1);
                        hBox.getChildren().add(handsOfBot.get(i));
                        centerHand.setGraphic(hBox);
                        handsOfBot.remove(i);
                        handOfBot.remove(i);
                        checkTurn = 0;
                        System.out.println(checkTurn);
                        n--;

                        if(n == 0){
                            lose();
                        }
                        break;
                    }
                    else count++;
                }

                if(count == handOfBot.size()){
                    System.out.println("Bo luot");
                    count = 0;
                    currentHand = new CardRepresentative(0, 0,0);
                    checkTurn = 0;
                }
            }
            else {
                hBox.getChildren().clear();
                centerHand.setGraphic(null);
                System.out.println("Bo luot");
                currentHand = new CardRepresentative(0, 0,0);
                checkTurn = 0;
            }
        }
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/gambling.mp3", 1);
    }


    public void boBai(ActionEvent actionEvent) {
        hBox.getChildren().clear();
        centerHand.setGraphic(null);
        if(handOfBot.get(0).getRank().equals("J")){
            excepLen = 11;
        }
        else if(handOfBot.get(0).getRank().equals("Q")){
            excepLen = 12;
        }
        else if(handOfBot.get(0).getRank().equals("K")){
            excepLen = 13;
        }

        else if(handOfBot.get(0).getRank().equals("A")){
            excepLen = 14;
        }
        else if(handOfBot.get(0).getRank().equals("2")){
            excepLen = 15;
        }
        else excepLen = Integer.parseInt(handOfBot.get(0).getRank());
        currentHand = new CardRepresentative(1, excepLen,1);
        hBox.getChildren().add(handsOfBot.get(0));
        handOfBot.remove(0);
        handsOfBot.remove(0);
        centerHand.setGraphic(hBox);
        n--;
        if(n == 0){
            lose();
        }
        checkTurn = 0;
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/select-sound-121244.mp3", 0.7);
    }

    private void win(){
        result.setText("You win!");
        result.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->{
            SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/SamLocBotScene.fxml", "/com/example/playcardsfx/stylefile/SamLocGameStyle.css");
        } );
        pause.play();
    }

    private void lose(){
        result.setText("You lose!");
        result.setVisible(true);

        PauseTransition pause = new PauseTransition(Duration.seconds(3));
        pause.setOnFinished(event ->{
            SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/SamLocBotScene.fxml", "/com/example/playcardsfx/stylefile/SamLocGameStyle.css");
        } );
        pause.play();
    }
    public void resetButtonClicked(ActionEvent event){
        SceneManager.getInstance().setPrimaryStage((Stage)resetButton.getScene().getWindow());
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mixkit-water-sci-fi-bleep-902.mp3", 0.7);
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/SamLocBotScene.fxml", "/com/example/playcardsfx/stylefile/SamLocGameStyle.css");
    }

    public void homeButtonClick(MouseEvent event){
        SceneManager.getInstance().setPrimaryStage((Stage)homeButton.getScene().getWindow());
        MediaManager.getInstance().playClickSound("/MusicSource/EffectMusic/mixkit-water-sci-fi-bleep-902.mp3", 0.7);
        MediaManager.getInstance().playBackgroundMusic("/MusicSource/BackgroundMusic/retro-gaming-271301.mp3",0.5);
        SceneManager.getInstance().switchScene("/com/example/playcardsfx/fxmlfile/StartMenuScene.fxml", "/com/example/playcardsfx/stylefile/Style.css");
    }
}

