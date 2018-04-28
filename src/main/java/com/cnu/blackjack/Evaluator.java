package com.cnu.blackjack;

import lombok.Data;

import java.util.Map;

@Data
public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;
    private int playerScore;
    private int dealerScore;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
    }

    public void start() {
        dealerScore = dealer.getDealerScore();
        playerMap.forEach((name,player)-> {
            player = check16(player);
            if(player.getPlayerScore()==21){
                win(player);
            }else if(player.getPlayerScore()>21){
                lose(player);
            }else if(player.getPlayerScore() > dealerScore){
                win(player);
            }else if(player.getPlayerScore() <= dealerScore){
                if(dealerScore > 21){
                    win(player);
                }else{
                    lose(player);
                }
            }


        });
    }

    public void win(Player player){
        player.setBalance(player.getCurrentBet() * 2 + player.getBalance());
    }

    public void lose(Player player) {
        //Player class의 Playerbet()에서 배팅과동시에 금액을 제하므로 2번 출금하게 됨
        //player.setBalance(player.getBalance() - player.getCurrentBet());
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
