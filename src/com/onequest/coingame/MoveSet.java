package com.onequest.coingame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import com.google.gson.Gson;

public class MoveSet {
    Boolean isSuccess;
    int moveCount = 0;
    List<Move> moves;

    public MoveSet() {
        moves = new LinkedList<Move>();
    }

    public void add(Move move) {
        moves.add(move);
        moveCount++;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
    
    public static boolean export(MoveSet moveSet){
        
        String file = "illegal.json";
        Gson gson = new Gson();
        
        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(moveSet);

        try {
            // write converted json data to a file named "/data/**current time**.json"
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        
        return true;
    }

    public boolean export() {

        //System.out.println(this);
        
        String count = moveCount < 10 ? "0" + moveCount : String.valueOf(moveCount);
        String file = "illegal.json";
        Gson gson = new Gson();
        if(isSuccess)
            file = "data/"+ count +"success" + String.valueOf(System.currentTimeMillis()) + ".json";

        // convert java object to JSON format,
        // and returned as JSON formatted string
        String json = gson.toJson(this);

        try {
            // write converted json data to a file named "/data/**current time**.json"
            FileWriter writer = new FileWriter(file);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
    }

    @Override
    public String toString() {
        return "MoveSet [ isSuccess=" + isSuccess + ",  + moves=" + moves + "]";
    }

    /**
     * For exporting illegal moves
     * @param file
     * @return
     */
    public static MoveSet read(File file) {

        Gson gson = new Gson();

        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            // convert the json string back to object
            MoveSet obj = gson.fromJson(br, MoveSet.class);
            
            //System.out.println(obj);
            
            return obj;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
