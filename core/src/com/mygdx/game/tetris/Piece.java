package com.mygdx.game.tetris;

import com.mygdx.game.Tools;

import javax.tools.Tool;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Piece {
    private static final String TAG = Piece.class.getSimpleName();

    // possibilite de remplacer par un ENUM
    Map<String, int[][]> pieces=new HashMap<>();
    // figures sous forme de coordonnes de points autour de (0,0)
    private int[][] I={{ 0,-2},{0,-1},{0, 0},{ 0, 1}};
    private int[][] O={{ 0, 0},{1, 0},{1,-1},{ 0,-1}};
    private int[][] T={{-1,0}, {0, 0},{1, 0},{ 0, 1}};
    private int[][] L={{ 1,-1},{0,-1},{0, 0},{ 0, 1}};
    private int[][] J={{-1,-1},{0,-1},{0, 0},{ 0, 1}};
    private int[][] Z={{ 0, 1},{0, 0},{1, 0},{ 1,-1}};
    private int[][] S={{ 1, 1},{1, 0},{0, 0},{ 0,-1}};

    private String[] piecesName={"I", "O", "T", "L", "J", "Z", "S"};
    private static Random random=new Random();
    private Map<String, Integer> stats=new TreeMap<>();
    private Map<String, Integer> distances=new TreeMap<>();
    private Map<String, Float> proba=new TreeMap<>();
    private int statsSomme=0;

    private String currentPieceName=null;
    private int[][] currentPiece=null;

    public Piece() {
        pieces.put("I", this.I);
        pieces.put("O", this.O);
        pieces.put("T", this.T);
        pieces.put("L", this.L);
        pieces.put("J", this.J);
        pieces.put("Z", this.Z);
        pieces.put("S", this.S);

        stats.put("I", 0);
        stats.put("O", 0);
        stats.put("T", 0);
        stats.put("L", 0);
        stats.put("J", 0);
        stats.put("Z", 0);
        stats.put("S", 0);

        randomPiece();
        nextPiece();
    }

    public int[][] nextPiece() {
        return randomPiece();
    }

    private int[][] randomPiece() {
        int max=0;
        statsSomme=0;
        for(Map.Entry<String, Integer> entry : stats.entrySet()) {
            int valeur = entry.getValue();
            statsSomme+=valeur;

            if (valeur > max) {
                max=valeur;
            }
        }

        int sommeDistance=0;
        for(Map.Entry<String, Integer> entry : stats.entrySet()) {
            int valeur = entry.getValue();

            distances.put(entry.getKey(), max+1-valeur);
            sommeDistance+=max+1-valeur;
        }

        float sommeProba=0f;
        for(Map.Entry<String, Integer> entry : distances.entrySet()) {
            sommeProba+=(float)entry.getValue()/sommeDistance;
            proba.put(entry.getKey(), sommeProba);
        }

        float value=random.nextFloat();
        for(Map.Entry<String, Float> entry : proba.entrySet()) {
            if (entry.getValue() > value) {
                currentPieceName=entry.getKey();
                break;
            }
        }

        currentPiece=pieces.get(currentPieceName);
        incrementeStats(currentPieceName);

        return getCurrentPiece();
    }

    public void incrementeStats(String piece) {
        stats.put(piece, stats.get(piece)+1);
    }

    public int[][] getCurrentPiece() {
        return currentPiece;
    }

    public void rotateLeft() {
        // x, y ==> y, -x
        for(int i=0; i<currentPiece.length; i++) {
            int[] point=currentPiece[i];
            currentPiece[i]=new int[]{point[1], -point[0]};
        }
    }

    public void rotateRight() {
        // x, y ==> -y, x
        for(int i=0; i<currentPiece.length; i++) {
            int[] point=currentPiece[i];
            currentPiece[i]=new int[]{-point[1], point[0]};
        }
    }

    public void resumeCurrentPiece() {
        Tools.debug(TAG, toString());
    }

    public String toString() {
        String result=currentPieceName+": {";

        for(int[] point : currentPiece) {
            result+="{"+point[0]+","+point[1]+"}";
        }

        return result+"}";
    }

    public Map<String, Integer> getStats() {
        return stats;
    }

    public int getStatsSomme() {
        return statsSomme;
    }

}
