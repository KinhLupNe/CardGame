package com.example.playcardsfx.model.enities;

import java.util.ArrayList;
import java.util.Collections;

public class Deck{
    private ArrayList<Card> deck;
    public Deck(){
        deck = new ArrayList<>();
        String[] suits = {"♣", "♦", "♥", "♠"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        //Tạo mảng gồm 36 lá bài
        for(String suit : suits){
            for(String rank : ranks){
                deck.add(new Card(rank, suit));
            }
        }
    }
    public Deck(int i){
        deck = new ArrayList<>();
        String[] suits = {"♣", "♦", "♥", "♠"};
        String[] ranks = {"A", "2", "3", "4", "5", "6", "7", "8", "9"};

        //Tạo mảng gồm 36 lá bài
        for(String suit : suits){
            for(String rank : ranks){
                deck.add(new Card(rank, suit));
            }
        }
    }
    //xào bài
    public void shuffle(){
        Collections.shuffle(deck);
    }

    //chia bài
    public Card dealCard(){
        return deck.remove(0);
    }
    //test trong Main




}
