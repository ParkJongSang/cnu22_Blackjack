package com.cnu.blackjack;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;

public class PlayerTest {

    private Hand hand;

    @Before
    public void setUp() {
        hand = new Hand(new Deck(1));
    }

    @Test
    public void 플레이어는_시드머니를_가지고_시작한다() {
        Player player = new Player(5000, hand);
        assertThat(player.getBalance(), is(5000));
    }

    @Test
    public void 플레이어는_배팅을_할수_있어야한다() {
        Player player = new Player(10000, hand);
        player.placeBet(1000);
        int currentBet = player.getCurrentBet();
        assertThat(currentBet, is(1000));
    }

    @Test(expected = Exception.class)
    public void 플레이어는_발란스_이하로_배팅할수_없다() {
        Player player = new Player(5000, hand);
        player.placeBet(6000);
    }

    @Test
    public void 플레이어는_카드를_HIT_할수_있어야한다() {
        Player player = new Player(5000, hand);
        assertThat(player.hitCard(), notNullValue());
    }

    @Test
    public void 플레이어는_핸드의_점수를_반환할_수_있다() {
        Player player = new Player(5000, hand);
        Card firstCard = player.hitCard();
        Card secondCard = player.hitCard();
        int playerScore = player.getPlayerScore();
        int totalScore = firstCard.getScore(firstCard.getRank())
                + secondCard.getScore(secondCard.getRank());
        assertTrue(playerScore==totalScore);
    }
}
