package com.onequest.coingame;

public class Move implements Cloneable {
    
    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    int moveNumber;
    Board from;
    int flip;
    boolean legal;

    public Move(Board from, int flip, int moveNumber, boolean legal) {
        this.from = (Board) from.clone();
        this.flip = flip;
        this.legal = legal;
        this.moveNumber = moveNumber;
    }

    @Override
    public String toString() {
        String output = moveNumber + ":" + from + ":" + flip + ":" + legal;

        return output;
    }

    public int getFlip() {
        return flip;
    }

    public void setFlip(int flip) {
        this.flip = flip;
    }

    public boolean isLegal() {
        return legal;
    }

    public void setLegal(boolean legal) {
        this.legal = legal;
    }

    public Board getFrom() {
        return from;
    }

    public void setFrom(Board from) {
        this.from = from;
    }
}
