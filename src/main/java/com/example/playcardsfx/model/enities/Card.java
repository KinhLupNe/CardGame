package com.example.playcardsfx.model.enities;

public class Card{
    //Tạo thuộc tính điểm bài và chất bài
    private String suit;
    private String rank;

    public Card(String rank, String suit){
        this.suit = suit;
        this.rank = rank;
    }

    public String getSuit(){
        return suit;
    }
    public String getRank(){
        return rank;
    }

    public String getCardIndex(){
        return rank + " " + suit;
    }
}

