package com.cnu.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class EvaluatorTest {
    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {
        Game game=new Game(new Deck(1));
        game.addPlayer("user1",100);
        game.addPlayer("user2",100);
        game.addPlayer("user3",100);
        Evaluator evaluator=new Evaluator(game.getPlayerList());

        evaluator.getPlayerMap().forEach((name,player)->{
            assertTrue(player.getHand().getCardList().size()==2);
        });
    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {
    //16이하인 경우 hit를 하므로 정상적으로 동작한다면 16이하의 점수를 가질 수 없음
        Game game=new Game(new Deck(1));
        game.addPlayer("user1",1000);
        Evaluator evaluator=new Evaluator(game.getPlayerList());
        Player testplayer=evaluator.getPlayerMap().get("user1");
        System.out.print("처음받은 카드 2개의 점수\" "+testplayer.getPlayerScore()+ "\"가 ");
        evaluator.start();
        System.out.println("16이하인 경우 hit : "+testplayer.getPlayerScore());
        assertTrue(testplayer.getPlayerScore()>16);
        //
    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {


    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {

    }
}
