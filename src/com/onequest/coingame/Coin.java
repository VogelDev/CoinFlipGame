package com.onequest.coingame;

public class Coin {
    boolean facing;
    String orient;
    private final String head = "Heads", tail = "Tails";
    
    public Coin(){
        facing = true;
        orient = head;
    }
    
    /**
     * For heads, insert true. For tails, false
     * @param heads
     */
    public Coin(boolean heads){
        this.facing = heads;
        setOrient();
    }

    private void setOrient() {
        if(facing)
            orient = head;
        else
            orient = tail;
    }
    
    public void flip(){
        facing = !facing;
        setOrient();
    }
    
    /**
     * Returns true for heads, false for tails
     * @return
     */
    public boolean getFacing(){
        return facing;
    }
    
    public boolean equals(Object obj){
        if(obj instanceof Coin){
            Coin coin = (Coin) obj;
            if(this.getFacing() == coin.getFacing())
                return true;
            else
                return false;
        }
        
        return false;
    }

    @Override
    public String toString() {
        return orient;
    }
    
}
