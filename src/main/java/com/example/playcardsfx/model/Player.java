package com.example.playcardsfx.model;

import java.util.ArrayList;

public class Player {
    private String name;
    private ArrayList<Card> cardsOfPlayer;

    public Player(String name) {
        this.name = name;
        cardsOfPlayer = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    // Thêm các lá bài vào hand
    public void addCard(Card card) {
        cardsOfPlayer.add(card);
    }
    // Trả về các lá bài
    public ArrayList<Card> getCardsOfPlayer() {
        return cardsOfPlayer;
    }

    public Card getCardOfPlayer(int index){
        return cardsOfPlayer.get(index);
    }
    //Rút bài khỏi hand
    public void removeCardsOfPlayer(ArrayList<Card> cards) {
        cardsOfPlayer.removeAll(cards);
    }

}
