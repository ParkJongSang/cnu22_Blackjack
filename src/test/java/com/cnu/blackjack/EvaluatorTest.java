package com.cnu.blackjack;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class EvaluatorTest {
    private Game game;

    @Before
    public void 시작_전_게임을_만든다() {
        game=new Game(new Deck(1));
        game.addPlayer("user1",1000);
    }

    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {
        game.addPlayer("user2",1000);
        game.addPlayer("user3",1000);
        Evaluator evaluator=new Evaluator(game.getPlayerList());

        evaluator.getPlayerMap().forEach((name,player)-> assertTrue(player.getHand().getCardList().size()==2));
    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {
    //16이하인 경우 hit를 하므로 정상적으로 동작한다면 16이하의 점수를 가질 수 없음
        Evaluator evaluator=new Evaluator(game.getPlayerList());
        Player testplayer=evaluator.getPlayerMap().get("user1");
        System.out.print("처음받은 카드 2개의 점수\" "+testplayer.getPlayerScore()+ "\"가 ");
        evaluator.start();
        System.out.println("16이하인 경우 hit : "+testplayer.getPlayerScore());
        assertTrue(testplayer.getPlayerScore()>16);
    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {
        Evaluator evaluator=new Evaluator(game.getPlayerList());
        Player testplayer = evaluator.getPlayerMap().get("user1");
        testplayer.placeBet(100);
        testplayer.setScore(21);
        //블랙잭일 경우 이긴경우이기 때문에 win실행
        evaluator.win(testplayer);

        assertTrue(testplayer.getBalance() == 1100);

    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {
        //16이하인 경우 hit를 하므로 정상적으로 동작한다면 16이하의 점수를 가질 수 없음
        Evaluator evaluator=new Evaluator(game.getPlayerList());
        Player testplayer=evaluator.getPlayerMap().get("user1");
        //플레이어의 카드점수가 17이상인 경우 카드의 개수가 변하지 않음
        int before_card_size=testplayer.getHand().getCardList().size();
        if(testplayer.getPlayerScore()>=17){
            evaluator.start();
            int after_card_size=testplayer.getHand().getCardList().size();
            assertEquals(before_card_size,after_card_size);
        }

    }
}
