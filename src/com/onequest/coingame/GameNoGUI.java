package com.onequest.coingame;

import java.util.Scanner;

/**
 * Single player game, text interface.
 * @author Rob Vogel
 *
 */
public class GameNoGUI {
    public static void main(String[] a) {
        boolean playing = true;
        @SuppressWarnings("resource")
        Scanner in = new Scanner(System.in);
        Board board = new Board(false, true, false);
        System.out
                .println("Enter 0, 1 or 2 to flip a coin.\nYou can flip the center coin anytime.\nYou can only flip an end coin if the remaing two show the same.");
        
        while(playing){
            while(!board.isOver()){
                System.out.println(board.toString());
                try{
                    board.flip(in.nextInt());
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
            System.out.println("Gameover, again?\nY or N\n");
            if(in.next().equalsIgnoreCase("Y")){
                board = new Board();
            }else{
                System.exit(0);
            }
        }
    }
}
