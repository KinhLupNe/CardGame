package com.example.playcardsfx.model.gamelogic.bacay;

import com.example.playcardsfx.model.enities.Card;

import java.util.ArrayList;

public class BaCayLogic {
    ArrayList<Card> cards;

    public BaCayLogic(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public int countOfCardsIndex(ArrayList<Card> cards) {
        int sum = 0;

        for(int i = 0 ; i < 3; i++){
            String n = cards.get(i).getCardIndex();
            int k;
            if(String.valueOf(n.charAt(0)).equals("A")){
                k = 1;
            }
            else {
                k = Integer.parseInt(String.valueOf(n.charAt(0)));
            }
            sum+= k;
        }
        String leaf1 = cards.get(0).getCardIndex();
        String leaf2 = cards.get(1).getCardIndex();
        String leaf3 = cards.get(2).getCardIndex();
        //Trường hợp đặc biệt 3 quân bài giống nhau
        if(String.valueOf(leaf1.charAt(0)).equals(String.valueOf(leaf2.charAt(0))) && String.valueOf(leaf1.charAt(0)).equals(String.valueOf(leaf3.charAt(0)))){
            if(String.valueOf(leaf1.charAt(0)).equals("A")){
                sum = 1000;
            }
            else sum = Integer.parseInt(String.valueOf(leaf1.charAt(0))) * 100;

            //Sáp át
        }

        if(11<= sum && sum<=20){
            return sum - 10;
        }

        else if(21<= sum && sum<=30){
            return sum - 20;
        }
        return sum;
    }

}
