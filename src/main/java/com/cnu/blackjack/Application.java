package com.cnu.blackjack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        String playerName;
        int seedMoney, playerNumber;

        Scanner scan = new Scanner(System.in);

        System.out.print("플레이어 수 입력 ");
        playerNumber = scan.nextInt();
        System.out.print("덱 개수 설정 ");
        int deckcount = scan.nextInt();
        Deck deck = new Deck(deckcount);
        Game game = new Game(deck);

        for (int i = 0; i < playerNumber; i++) {

            System.out.println("플레이어 " + (i + 1) + " 이름 설정");
            playerName = scan.next();

            System.out.println("플레이어 " + (i + 1) + " 시드머니 설정");
            seedMoney = scan.nextInt();
            game.addPlayer(playerName, seedMoney);
        }

        System.out.println("=================게임시작===============");

        while (game.getPlayerList().size() != 0) {
            System.out.println("배팅 금액을 입력합니다.");
            game.getPlayerList().forEach((name, player) -> {
                //*************************************************
                player.getHand().cardList=new ArrayList<>();
                //**************************************************
                System.out.println("( 현재 " + name + "의 잔액 : " + player.getBalance() + " )");
                System.out.print(name + "의 베팅할 금액을 설정하세요 :");
                int bet = scan.nextInt();
                player.placeBet(bet);
            });

            Evaluator evaluator = new Evaluator(game.getPlayerList());
            evaluator.start();

            //딜러의 스코어와 플레이어들의 스코어, 남은금액 출력
            System.out.println("=====================================");
            System.out.println("딜러의 스코어 : " + evaluator.getDealerScore());
            System.out.println("=====================================");
            game.getPlayerList().forEach((name, player) -> {

                System.out.println(name + " 의 결과 > " + (player.isResult()?"win": "lose"));
                System.out.println(name + "의 스코어 : " + player.getPlayerScore());
                System.out.print("*** " + name + "의 카드는 ");
                for (int i = 0; i < player.getHand().getCardList().size(); i++) {
                    System.out.print("(" + player.getHand().getCardList().get(i).getSuit() + " / ");
                    System.out.print(player.getHand().getCardList().get(i).getRank() + ") \t");
                }
                System.out.println("\n" + name + "의 남은금액 : " + player.getBalance());
                System.out.println("-------------------------------------");
                System.out.println("=====================================");
            });

            Iterator it = evaluator.getPlayerMap().values().iterator();
            while (it.hasNext()) {
                Player removePlayer = (Player) it.next();
                if (removePlayer.getBalance() <= 0) {
                    it.remove();
                }
            }
            System.out.println("계속 진행? (Y/N)");
            if (scan.next().equals("N")) {
                break;
            }
            System.out.println("\n\n");
        }


        //System.out.println(game.getPlayerList());
        System.out.println("종료");
    }
}
