package com.mygdx.game.tetris;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.Tools;
import com.mygdx.game.screens.GlobalScreen;
import com.mygdx.game.screens.MenuUI;

// pas super propre de faire un InputProcessor ici, mais c'est au plus rapide
public class GridScreen extends GlobalScreen implements InputProcessor {
    private MenuUI _menuUI;
    private Grid _grid;
    private Sprite cube, empty, piece;
    private int _spriteWidth=20, _spriteHeight=20;
    private float _timer=0f, _duration=3f;

    private static final String TAG = GridScreen.class.getSimpleName();

    public GridScreen(ScreenManager manager) {
        _manager=manager;

        initGridScreen();
    }

    private void initGridScreen() {
        _grid=new Grid();
        batch=new SpriteBatch();

        setupViewport(800, 600);
        _camera = new OrthographicCamera();
        _camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);

        _menuUI=new MenuUI(_manager);

        _multiplexer = new InputMultiplexer();
        _multiplexer.addProcessor(this);
        _multiplexer.addProcessor(_menuUI.getStage());
        batch=(SpriteBatch)_menuUI.getStage().getBatch();

        Gdx.input.setInputProcessor(_multiplexer);

        TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("gui/ui_rpg.pack"));
        cube=new Sprite(atlas.findRegion("buttonSquare_beige_pressed"));
        empty=new Sprite(atlas.findRegion("buttonSquare_grey_pressed"));
        piece=new Sprite(atlas.findRegion("buttonSquare_blue_pressed"));


        buildGrid();
    }

    public void buildGrid() {
        _grid.todo();

    }

    private void renderGrid() {
        for (int line=_grid.getGrid().length-1; line>=0; line--) {
            for (int row=0; row<_grid.getGrid()[line].length; row++) {
                if (_grid.getGrid()[line][row]== 0) {
                    batch.draw(empty, 100+row*_spriteWidth, line*_spriteHeight, _spriteWidth, _spriteHeight);
                }
                else {
                    batch.draw(cube, 100+row*_spriteWidth, line*_spriteHeight, _spriteWidth, _spriteHeight);
                }
            }
        }
    }

    private void renderPiece() {
        Vector2 position=_grid.getCurrentPosition();

        for (int[] point : _grid.getCurrentPiece()) {
            batch.draw(piece, 100+(point[0]+position.x)*_spriteWidth, (point[1]+position.y)*_spriteHeight, _spriteWidth, _spriteHeight);
        }
    }

    private void update(float delta) {
        _timer+=delta;

        if (_timer > _duration) {
            _timer=0;
            _grid.moveDown();
        }
    }


    @Override
    public void render(float delta) {
        update(delta);

        batch.setProjectionMatrix(_camera.combined);
        batch.begin();

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        renderGrid();
        renderPiece();

        batch.end();

        _menuUI.render(delta);
    }

    @Override
    public void dispose() {
        _menuUI.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        if( keycode == Input.Keys.LEFT){
            _grid.moveLeft();
        }

        if( keycode == Input.Keys.RIGHT){
            _grid.moveRight();
        }

        if( keycode == Input.Keys.DOWN){
            _grid.moveDown();
        }

        if( keycode == Input.Keys.A){
            _grid.rotateLeft();
        }

        if( keycode == Input.Keys.Z){
            _grid.rotateRight();
        }

        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}