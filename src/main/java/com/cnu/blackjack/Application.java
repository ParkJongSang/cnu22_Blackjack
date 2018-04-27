package com.cnu.blackjack;

        import java.util.Scanner;

public class Application {
    public static void main(String[] args) {

        String playerName;
        int seedMoney, playerNumber;

        Scanner scan = new Scanner(System.in);

        System.out.print("플레이서 수 입력 ");
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
        while(true) {
            System.out.println("배팅 금액을 입력합니다.");
            game.getPlayerList().forEach((name, player) -> {
                System.out.print("베팅할 금액을 설정하세요 "+name+" :");
                int bet = scan.nextInt();
                player.placeBet(bet);
            });

            Evaluator evaluator = new Evaluator(game.getPlayerList());
            evaluator.start();
            System.out.println("=====================================");
            System.out.println("딜러의 스코어 : " + evaluator.getDealerScore());
            System.out.println("=====================================");
            game.getPlayerList().forEach((name, player) -> {
                System.out.println(name + "의 스코어 : " + player.getPlayerScore());
                System.out.println(name + "의 남은금액 : " + player.getBalance());
                System.out.println("-------------------------------------");
            });
            System.out.println("계속 진행? (Y/N)");
            if(scan.next().equals("N")) {
                break;
            }
        }


        System.out.println(game.getPlayerList());
        System.out.println("종료");
    }
}
