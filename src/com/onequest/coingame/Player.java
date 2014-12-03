package com.onequest.coingame;

import java.io.File;
import java.util.Random;

public class Player {
    MoveSet currentSet;
    MoveSet[] data;
    MoveSet illegal;
    Board board;
    Random random;
    int choice;

    public void init() {
        data = null;
        currentSet = new MoveSet();
        illegal = importIllegal();

        random = new Random();

        importData();
    }

    private MoveSet importIllegal() {
        File file = new File("illegal.json");

        if (MoveSet.read(file) == null)
            return new MoveSet();
        else
            return MoveSet.read(file);
    }

    private void importData() {
        File folder = new File("data/");
        File[] list = folder.listFiles();

        if (list == null)
            return;

        data = new MoveSet[list.length];

        for (int i = 0; i < list.length; i++) {
            if (list[i].isFile()) {
                data[i] = MoveSet.read(list[i]);
            }
        }

        /*
         * For viewing historical data
        for (MoveSet set : data) {
            for (Move move : set.getMoves()) {
                //System.out.println(move.getFrom() + ", " + move.getFlip()
                //        + move.isLegal());
            }
        }
        */
    }

    private int checkData() {
        File folder = new File("data/");
        File[] list = folder.listFiles();

        if (list == null)
            return 0;

        return list.length;
    }

    public Player() {
        init();
    }

    public int takeTurn(Board board) {
        random = new Random();
        do {
            choice = random.nextInt(3);
        } while (!legalMove(board, choice));

        return choice;
    }

    public MoveSet getCurrentSet() {
        return currentSet;
    }

    public void setCurrentSet(MoveSet currentSet) {
        this.currentSet = currentSet;
    }

    private boolean legalMove(Board board, int i) {
        // System.out.println(checkData());
        if (checkData() % 10 == 0) {
            // System.out.println("checking data");
            importData();
            importIllegal();
        }

        if (illegal == null){
            //System.out.println("No Illegal");
            return true;
        }

        for (Move move : illegal.getMoves()) {
            if (move.getFrom().equals(board) && move.getFlip() == i
                    && !move.isLegal()) {
                //System.out.println("old illegal");
                return false;
            }
        }

        if (checkData() == 0) {
            //System.out.println("no data");
            return true;
        }

        
        Move shortest = null;
        int count = Integer.MAX_VALUE;
        for (MoveSet set : data) {
            for (Move move : set.getMoves()) {
                if (move.getFrom().equals(board)) {
                    if(count > set.moveCount - move.moveNumber){
                        count = set.moveCount - move.moveNumber;
                        shortest = move;
                        //System.out.println(shortest + ", " + count);
                    }
                }
            }
        }
        
        if(shortest != null){
            if(shortest.getFlip() == i){
                return true;
            }else{
                return false;
            }
        }
        
        

        //System.out.println("haven't seen this before");

        return true;
    }

    public MoveSet getIllegal() {
        return illegal;
    }

    public void setIllegal(MoveSet illegal) {
        this.illegal = illegal;
    }
}
