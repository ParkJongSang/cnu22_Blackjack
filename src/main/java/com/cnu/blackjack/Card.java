package com.cnu.blackjack;

import com.cnu.blackjack.exceptions.NoSuchRankException;
import lombok.Data;

@Data
public class Card {
    private final int rank;
    private final Suit suit;

    public Card(int rank, Suit suit) {
        if (rank > 13) {
            throw new NoSuchRankException();
        }
        this.rank = rank;
        this.suit = suit;
    }

    //각 카드의 점수를 반환해줌
    public int getScore(int rank){
        if(rank == 1){
            return 11;
        }else if(rank >= 10) {
            return 10;
        }else{
            return rank;
        }
    }
}
