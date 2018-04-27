package com.cnu.blackjack;

import lombok.Data;

import java.util.Map;

@Data
public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
    }

    public Map<String, Player> getPlayerMap() {
        return this.playerMap;
    }

    public void start() {
        playerMap.forEach((name,player)-> {
            player=check16(player);
            if(player.getPlayerScore()==21){
               // win();
            }
            else if(player.getPlayerScore()>21){
              //  lose();
            }

        });
            }

    private Player check16(Player player){
        int score=player.getPlayerScore();
        if(score<=16){
            player.hitCard();
            check16(player);
        }
        return player;

    }


    private void dealCardToPlayers() {
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }
}
