package com.mygdx.game.tetris;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools;
import com.sun.corba.se.impl.oa.toa.TOA;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Grid {
    private static final String TAG = Grid.class.getSimpleName();

    private int _width=10, _height=22;
    private int[][] _grid;
    private Piece _currentPiece;
    private Vector2 _currentPosition;

    ////// pour les tests
    private int[] a={0, 0, 1, 1, 0, 1, 1, 1, 1, 1};
    private int[] b={0, 0, 1, 1, 0, 1, 1, 1, 1, 1};
    private int[] c={0, 1, 1, 1, 0, 1, 1, 1, 1, 0};
    private int[] d={1, 1, 1, 1, 0, 1, 1, 1, 1, 0};

    public Grid() {
        initGrid();
    }

    public Grid(int width, int height) {
        _height=height;
        _width=width;

        initGrid();
    }

    private void initGrid() {
        _grid=new int[_height][_width];
        for (int[] row : _grid ) {
            Arrays.fill(row, 0);
        }
    }

    public void todo() {
        addTest();
        _currentPiece=new Piece();
        setInitialPosition();
    }

    private void getNewPiece() {
        _currentPiece.nextPiece();
        setInitialPosition();
    }

    private void setInitialPosition() {
        _currentPosition=new Vector2(_width/2, _height-1);

        if (!testCollisionBorder(new Vector2(_currentPosition.x, _currentPosition.y))) {
            if (testCollisionBorder(new Vector2(_currentPosition.x, _currentPosition.y-1))) {
                _currentPosition.y--;
            }
            else if (testCollisionBorder(new Vector2(_currentPosition.x, _currentPosition.y-2))) {
                _currentPosition.y-=2;
            }
            else {
                Tools.debug(TAG, "GAME OVER");
            }
        }
    }

    public void addTest() {
        _grid[0]=a;
        _grid[1]=b;
        _grid[2]=c;
        _grid[3]=d;
    }

    public void checkGrid() {
        /*
        1- pose d'une piece dans le tableau
        2- parcourir les lignes
        3- si une ligne est faite
            a- supprimer la ligne
            b- décaler les lignes vers le bas
        4- continuer avec une nouvelle pièce
         */
    }

    private void addPieceToGrid() {
        for(int[] point : _currentPiece.getCurrentPiece()) {
            _grid[(int)_currentPosition.y+point[1]][(int)_currentPosition.x + point[0]]=1;
        }
    }

    public boolean testCollisionBrick(Vector2 position) {
        for(int[] point : _currentPiece.getCurrentPiece()) {
            if ((_grid[(int)position.y + point[1]][(int)position.x + point[0]] != 0) ||
                    (_grid[(int)position.y + point[1]][(int)position.x + point[0]] != 0)) {
                Tools.debug(TAG, "VALEUR "+(int)(position.x + point[0])+" / "+(int)(position.y + point[1])+" = "+_grid[(int)position.y + point[1]][(int)position.x + point[0]]);
                return false;
            }
        }

        return true;
    }

    public boolean testCollisionBorder(Vector2 position) {
        for(int[] point : _currentPiece.getCurrentPiece()) {
            if ((position.x + point[0] < 0) || (position.x + point[0] >= _width)) {
                return false;
            }

            if ((position.y+point[1] < 0) || (position.y+point[1] >= _height)) {
                Tools.debug(TAG, "bordure Y, "+position.y+" + "+point[1]+" VS "+_height);
                return false;
            }
        }

        return true;
    }

    private boolean noCollision(int dx, int dy) {
        return testCollisionBorder(new Vector2(_currentPosition.x+dx, _currentPosition.y+dy)) && testCollisionBrick(new Vector2(_currentPosition.x+dx, _currentPosition.y+dy));
    }


    // @TODO modifier le code de rotation de la barre
    public void rotateLeft() {
        _currentPiece.rotateLeft();
        if (!noCollision(0, 0)) {
            _currentPiece.rotateRight();
        }
    }

    // @TODO modifier le code de rotation de la barre
    public void rotateRight() {
        _currentPiece.rotateRight();
        if (!noCollision(0, 0)) {
            _currentPiece.rotateLeft();
        }
    }

    public void moveLeft() {
        if (noCollision(-1, 0)) {
            _currentPosition.x--;
        }
    }

    public void moveRight() {
        if (noCollision(1, 0)) {
            _currentPosition.x++;
        }
    }

    public void moveDown() {
        if (noCollision(0, -1)) {
            _currentPosition.y--;
        }
        else {
            addPieceToGrid();
            getNewPiece();
        }
    }

    public void showGrid() {
        for (int line=_height-1; line>=0; line--) {
            showLine(line);
            isCompleteLine(line);
        }
    }

    public void showLine(int line) {
        String result=line+":\t";
        for (int x=0; x<_width; x++) {
            result+=_grid[line][x]+",";
        }

        Tools.debug(TAG, result);
    }

    public void isCompleteLine(int line) {
        int sum = IntStream.of(_grid[line]).sum();

        if (sum == _width) {
            Tools.debug(TAG, "ligne complete ++++++++++++++++++++++");
        }
    }

    public int[][] getGrid() {
        return _grid;
    }

    public int[][] getCurrentPiece() {
        return _currentPiece.getCurrentPiece();
    }

    public Vector2 getCurrentPosition() {
        return _currentPosition;
    }
}
