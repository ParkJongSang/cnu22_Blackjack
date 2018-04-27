package com.cnu.blackjack;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class EvaluatorTest {

    private Game game;

    @Test
    public void 게임초기화시_모든플레이어는_2장의카드를_받는다() {
        Evaluator evaluator = new Evaluator(game.getPlayerList());
        Assert.assertNotNull( evaluator.getPlayerMap().get("song").getHand() );
        Assert.assertNotNull( evaluator.getPlayerMap().get("park").getHand() );
    }

    @Test
    public void 각_플레이어는_16이하면_히트한다() {
    }

    @Test
    public void 블랙잭이나오면_2배로_보상받고_해당_플레이어의_턴은_끝난다() {
        Evaluator evaluator = new Evaluator(game.getPlayerList());
        //점수를 확인
    }

    @Test
    public void 각_플레이어는_17이상이면_스테이한다() {

    }
    // 아래부터 수정 부분
    @Before
    public void 테스트_전_게임을_생성한다() {
        game = new Game(new Deck(2));
        game.addPlayer("song", 5000);
        game.addPlayer("park", 5000);
    }

    @Test
    public void 딜러와_플레이어의_점수가_같으면_딜러가_이긴다() {

    }
}
