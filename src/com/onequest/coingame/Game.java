package com.onequest.coingame;

/**
 * Computer learning game, computer will learn the rules of the game.
 * @author Rob Vogel
 *
 */
public class Game {
    static Board board = new Board();
    static Move move;
    static Player player = new Player();

    public static void main(String[] a) {
        int games = 10;
        Board tmp;

        // System.out.println(board);

        while (games > 0) {
            System.out.println(games);
            while (!board.isOver()) {
                int choice = player.takeTurn(board);
                // System.out.println(board);
                // System.out.println(choice);
                try {
                    tmp = new Board(board.get(0).getFacing(), board.get(1).getFacing(), board.get(2).getFacing());
                    //System.out.println(board + ", " + choice);
                    board.flip(choice);

                    //System.out.println("temp" + tmp);
                    move = new Move(tmp, choice, player.getCurrentSet().moveCount, true);
                    player.getCurrentSet().add(move);
                    //System.out.println(player.getCurrentSet());
                } catch (Exception e) {
                    //System.out.println("failed");
                    //System.out.println(board + ", " + choice);
                    endGame(choice, false);
                    break;
                }
                if (board.isOver()) {
                    endGame(choice, true);
                    break;
                }
            }
            games--;
        }
    }

    private static void endGame(int choice, boolean legal) {
        move = new Move(board, choice, player.getCurrentSet().moveCount, legal);
        player.getCurrentSet().add(move);
        player.getCurrentSet().setSuccess(legal);
        //System.out.println(move);
        if (legal){
            if (player.getCurrentSet().export())
                System.out.println("Exported");
            
            System.out.println(player.getCurrentSet().moveCount);
        }else{
            player.getIllegal().add(move);
            MoveSet.export(player.getIllegal());
        }

        player = new Player();
        board = new Board();
    }
}
