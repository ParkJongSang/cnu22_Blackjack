package com.cnu.blackjack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Hand {
    private Deck deck;
    private List<Card> cardList = new ArrayList<Card>();

    public Hand(Deck deck) {
        this.deck = deck;
    }

    public Card drawCard() {
        Card card = deck.drawCard();
        cardList.add(card);
        return card;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public int getHandScore(){
        int totalScore = 0;
        Iterator it = cardList.iterator();
        while(it.hasNext()){
            Card currentCard = (Card)it.next();
            totalScore = totalScore + currentCard.getScore(currentCard.getRank());
        }
        return totalScore;
    }
}
