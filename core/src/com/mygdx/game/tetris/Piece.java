package com.mygdx.game.tetris;

import com.badlogic.gdx.math.MathUtils;
import com.mygdx.game.Tools;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class Piece {
    private static final String TAG = Piece.class.getSimpleName();

    private static enum PieceEnum {
        I(new int[][] {{ 0,-2},{0,-1},{0, 0},{ 0, 1}}),
        O(new int[][] {{ 0, 0},{1, 0},{1,-1},{ 0,-1}}),
        T(new int[][] {{-1,0}, {0, 0},{1, 0},{ 0, 1}}),
        L(new int[][] {{ 1,-1},{0,-1},{0, 0},{ 0, 1}}),
        J(new int[][] {{-1,-1},{0,-1},{0, 0},{ 0, 1}}),
        Z(new int[][] {{ 0, 1},{0, 0},{1, 0},{ 1,-1}}),
        S(new int[][] {{ 1, 1},{1, 0},{0, 0},{ 0,-1}});

        private int[][] _data;

        PieceEnum(int[][] data) {
            _data=data;
        }

        public int[][] getData() {
            return _data;
        }
    }

    private static Random random=new Random();
    private Map<String, Integer> stats=new TreeMap<>();
    private Map<String, Integer> distances=new TreeMap<>();
    private Map<String, Float> proba=new TreeMap<>();
    private int statsSomme=0;

    private String currentPieceName=null;
    private int[][] currentPiece=null;

    public Piece() {
        stats.put("I", 0);
        stats.put("O", 0);
        stats.put("T", 0);
        stats.put("L", 0);
        stats.put("J", 0);
        stats.put("Z", 0);
        stats.put("S", 0);

        nextPiece();
    }

    public int[][] nextPiece() {
        int max=0;
        statsSomme=0;
        for(Map.Entry<String, Integer> entry : stats.entrySet()) {
            int valeur = entry.getValue();
            statsSomme+=valeur;

            if (valeur > max) {
                max=valeur;
            }
        }

        statsSomme++;

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

        currentPiece=Enum.valueOf(PieceEnum.class, currentPieceName).getData();
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

    public boolean isO() {
        return currentPieceName.equals("O");
    }
}
