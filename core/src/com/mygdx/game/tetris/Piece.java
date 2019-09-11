package com.mygdx.game.tetris;

import com.mygdx.game.Tools;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

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

        nextPiece();
    }

    public int[][] nextPiece() {
        currentPieceName=piecesName[random.nextInt(piecesName.length)];
        currentPiece=pieces.get(currentPieceName);

        return getCurrentPiece();
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


}
