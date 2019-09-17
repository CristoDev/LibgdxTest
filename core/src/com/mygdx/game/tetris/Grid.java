package com.mygdx.game.tetris;

import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.Tools;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.IntStream;

public class Grid {
    private static final String TAG = Grid.class.getSimpleName();

    private int _width=10, _height=22, _nbLines=0;
    private int[][] _grid;
    private Piece _currentPiece;
    private Vector2 _currentPosition;

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
                // @TODO à faire
                Tools.debug(TAG, "GAME OVER");
            }
        }
    }

    private void addPieceToGrid() {
        for(int[] point : _currentPiece.getCurrentPiece()) {
            _grid[(int)_currentPosition.y+point[1]][(int)_currentPosition.x + point[0]]=1;
        }
    }

    public boolean testCollisionBrick(Vector2 position) {
        for(int[] point : _currentPiece.getCurrentPiece()) {
            if (_grid[(int) position.y + point[1]][(int) position.x + point[0]] != 0) {
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

    public boolean moveDown() {
        if (noCollision(0, -1)) {
            _currentPosition.y--;

            return false;
        }
        else {
            addPieceToGrid();
            checkCompleteLines();
            getNewPiece();

            return true;
        }
    }

    private void removeLine(int line) {
        for (int i=line; i<_height-1; i++) {
            _grid[i]=_grid[i+1].clone();
        }

        Arrays.fill(_grid[_height-1], 0);
    }

    private void checkCompleteLines() {
        resetNbLines();
        for (int line=_height-1; line>=0; line--) {
            if (isCompleteLine(line)) {
                removeLine(line);
                _nbLines++;
            }
        }
    }

    public void resetNbLines() {
        _nbLines=0;
    }

    public int getNbLines() {
        return _nbLines;
    }

    public boolean isCompleteLine(int line) {
        return (IntStream.of(_grid[line]).sum() == _width);
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

    public Map<String, Integer> getStats() {
        return _currentPiece.getStats();
    }

    public int getStatsSomme() {
        return _currentPiece.getStatsSomme();
    }
}
