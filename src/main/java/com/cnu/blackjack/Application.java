package com.cnu.blackjack;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        String playerName;
        int seedMoney, playerNumber;

        Scanner scan = new Scanner(System.in);

        System.out.println("플레이서 수 입력");
        playerNumber = scan.nextInt();
        System.out.println("덱 개수 설정");
        int deckcount = scan.nextInt();
        Deck deck = new Deck(deckcount);
        Game game = new Game(deck);

        for(int i =0; i < playerNumber; i++){

            System.out.println("플레이어 이름 설정");
            playerName = scan.next();

            System.out.println("플레이어 시드머니 설정");
            seedMoney = scan.nextInt();
            game.addPlayer(playerName, seedMoney);
        }

        Evaluator evaluator = new Evaluator(game.getPlayerList());


        System.out.println("=================게임시작===============");
        game.getPlayerList().forEach((name, player) -> {
            System.out.print("베팅할 금액을 설정하세요 "+name+" :");
            int bet = scan.nextInt();
            player.placeBet(bet);
        });

        System.out.println(game.getPlayerList());
        System.out.println("종료");
    }
}
