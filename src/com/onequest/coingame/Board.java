package com.onequest.coingame;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;

public class Board implements Cloneable {

    Coin[] coins;

    public Board() {
        coins = new Coin[3];
        coins[0] = new Coin(false);
        coins[1] = new Coin(true);
        coins[2] = new Coin(false);
    }

    /**
     * Sets up a new board with the coins at the given values. A fresh game
     * should be initialized as false, true, false
     * 
     * @param one
     * @param two
     * @param three
     */
    public Board(boolean one, boolean two, boolean three) {
        coins = new Coin[3];
        coins[0] = new Coin(one);
        coins[1] = new Coin(two);
        coins[2] = new Coin(three);
    }

    /**
     * Coin to flip, use 0, 1 or 2
     * 
     * throws <code>IllegalArgumentException</code> if argument is not from 0-2
     * 
     * @param coin
     */
    public boolean flip(int coin) throws IllegalArgumentException {
        if(coins[0].getFacing() && !coins[1].getFacing() && coins[2].getFacing()){
            throw new IllegalArgumentException("Game Over");
        }
        
        if (coin == 1) {
            coins[coin].flip();
            return true;
        }

        if (coin == 0) {
            if (coins[1].equals(coins[2])) {
                coins[coin].flip();
                return true;
            } else {
                throw new IllegalArgumentException("Illegal Move");
                //return false;
            }
        }

        if (coin == 2) {
            if (coins[1].equals(coins[0])) {
                coins[coin].flip();
                return true;
            } else {
                throw new IllegalArgumentException("Illegal Move");
                // return false;
            }
        }

        throw new IllegalArgumentException(
                "Only integers between 0-2 can be entered, found: " + coin);
    }

    public boolean isOver() {
        boolean isOver = coins[0].getFacing() && !coins[1].getFacing()
                && coins[2].getFacing();

        // System.out.println("Gameover: " + isOver);

        return isOver;
    }

    @Override
    public String toString() {
        int one = coins[0].getFacing() ? 1 : 0;
        int two = coins[1].getFacing() ? 1 : 0;
        int three = coins[2].getFacing() ? 1 : 0;
        String output = one + "" + two + "" + three;

        return output;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Board) {
            Board board = (Board) obj;
            if (coins[0].getFacing() == board.get(0).getFacing()
                    && coins[1].getFacing() == board.get(1).getFacing()
                    && coins[2].getFacing() == board.get(2).getFacing())
                return true;
            else
                return false;
        }

        return false;
    }

    public Coin get(int i) {
        return coins[i];
    }

    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean export() {

        Gson gson = new Gson();
        String file = "game.dat";

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(this);

        try {
            // write converted json data to a file named "game.dat"
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

}
