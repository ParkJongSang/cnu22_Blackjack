package com.cnu.blackjack;

import lombok.Data;

import java.util.Map;

@Data
public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;
    private int playerScore;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
    }

    public void start() {
        int dealerScore = getPlayerScore();
        playerMap.forEach((name,player)-> {
            player = check16(player);
            if(player.getPlayerScore()==21){
                win(player);
            }else if(player.getPlayerScore()>21){
                // lose();
            }else if(player.getPlayerScore() > dealerScore){
                win(player);
            }else if(player.getPlayerScore() <= dealerScore){
                if(dealerScore > 21){
                    //lose();
                }else{
                    win(player);
                }
            }

        });
    }

    public void win(Player player){
        int betMoney = player.getCurrentBet();
        int seedMoney = player.getBalance();
        int resultMoney = betMoney * 2 + seedMoney;
        player.setBalance(resultMoney);
    }

    private Player check16(Player player){
        if(player.getPlayerScore() <= 16){
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
