package learn.gomoku;

import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.NPCPlayer;
import learn.gomoku.ui.GameController;
import learn.gomoku.game.Gomoku;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        GameController controller = new GameController();
        controller.run();

        Scanner console = new Scanner(System.in);

        Player playerOne;
        System.out.println("Player 1 is: ");
        System.out.println("1. Human");
        System.out.println("2. NPC");
        System.out.print("Select [1-2]: ");
        String player1Choice = console.nextLine();

        if (player1Choice.equals("1")) {
            System.out.print("Ok player 1, please enter your name: ");
            String player1Name = console.nextLine();
            playerOne = new HumanPlayer(player1Name);
            System.out.println("Ok, " + player1Name);
        } else {
            playerOne = new NPCPlayer();
            System.out.println("Ok, " + playerOne.getName());
        }

        Player playerTwo;
        System.out.println("Player 2 is: ");
        System.out.println("1. Human");
        System.out.println("2. NPC");
        System.out.print("Select [1-2]: ");
        String player2Choice = console.nextLine();

        if (player2Choice.equals("1")) {
            System.out.print("Ok player 2, please enter your name: ");
            String player2Name = console.nextLine();
            playerTwo = new HumanPlayer(player2Name);
            System.out.println("Ok, " + playerTwo.getName());
        } else {
            playerTwo = new NPCPlayer();
            System.out.println("Ok, " + playerTwo.getName());
        }

        System.out.println("-------------------------------------------------------------------");
        System.out.printf("Today, we have %s taking on %s\n", playerOne.getName(), playerTwo.getName());
        System.out.println("-------------------------------------------------------------------");

        Gomoku gomoku = new Gomoku(playerOne, playerTwo);
        System.out.println(gomoku.getCurrent().getName() + " goes first!");

        System.out.println(gomoku.getCurrent().getName() + "'s turn");
//        console = new Scanner(System.in);
//        System.out.print("Enter a row: ");
//        String selectRow = console.nextLine();
//        System.out.print("Enter a column: ");
//        String selectColumn = console.nextLine();
//        printGame(gomoku.getStones());
//
//        int row = Integer.parseInt(selectRow) - 1;
//        int column = Integer.parseInt(selectColumn) - 1;
//        Stone humanStone = new Stone(row, column, gomoku.isBlacksTurn());
//        Result humanResult = gomoku.place(humanStone);
//        System.out.println(humanResult);

        printGame(gomoku.getStones());

        while(!gomoku.isOver()) {
            // print board and play game?
            Result result;
            do {
                System.out.println(gomoku.getCurrent().getName() + "'s move");
                Stone stone = gomoku.getCurrent().generateMove(gomoku.getStones());
                if (stone == null) {
                    console = new Scanner(System.in);
                    System.out.print("Enter a row: ");
                    String selectRow = console.nextLine();
                    System.out.print("Enter a column: ");
                    String selectColumn = console.nextLine();
//                    printGame(gomoku.getStones());

                    int row = Integer.parseInt(selectRow) - 1;
                    int column = Integer.parseInt(selectColumn) - 1;
                    stone = new Stone(row, column, gomoku.isBlacksTurn());
                }
                result = gomoku.place(stone);
                if (!result.isSuccess()) {
                    System.out.println(result);
                }
            } while (!result.isSuccess());
            printGame(gomoku.getStones());
        }
        String winner = gomoku.getWinner().getName();
        System.out.println("The winner is " + winner);
    }

    public static void printGame(List<Stone> stones) {
        char[][] board = new char[Gomoku.WIDTH][Gomoku.WIDTH];

        for (int i = 0; i < stones.size(); i++) {
            Stone stone = stones.get(i);
            int row = stone.getRow();
            int col = stone.getColumn();
            char symbol;
            if (stone.isBlack()) {
                symbol = 'B';
            } else {
                symbol = 'W';
            }
            board[row][col] = symbol;
        }

        System.out.print("   ");
        for (int col = 1; col <= Gomoku.WIDTH; col++) {
            System.out.printf("%2d ", col);
        }
        System.out.println();

        for (int row = 1; row <= Gomoku.WIDTH; row++) {
            System.out.printf("%2d ", row); // Print row number to the left
            for (int col = 1; col <= Gomoku.WIDTH; col++) {
                char symbol = board[row - 1][col - 1];
                if (symbol == 0) {
                    System.out.print(" _ ");
                } else {
                    System.out.print(" " + symbol + " ");
                }
            }
            System.out.println();
        }
    }
}