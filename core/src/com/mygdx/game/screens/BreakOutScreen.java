package com.mygdx.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.ScreenManager;
import com.mygdx.game.Tools;

public class BreakOutScreen  extends GlobalScreen implements InputProcessor {
    private OrthographicCamera camera;
    private ExtendViewport viewport;
    private ShapeRenderer shapes;

    public final static float SCALE = 32f;
    public final static float INV_SCALE = 1.f/SCALE;
    // this is our "target" resolution, not that the window can be any size, it is not bound to this one
    public final static float VP_WIDTH = 1280 * INV_SCALE;
    public final static float VP_HEIGHT = 720 * INV_SCALE;

    public BreakOutScreen(ScreenManager manager) {
        _manager=manager;
        init();
    }

    public void init() {
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(VP_WIDTH, VP_HEIGHT, camera);
        shapes = new ShapeRenderer();
        Gdx.input.setInputProcessor(this);

    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
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
    public boolean scrolled(int amount) {
        return false;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shapes.setProjectionMatrix(camera.combined);
        shapes.begin(ShapeRenderer.ShapeType.Filled);
        shapes.circle(tp.x, tp.y, 0.35f, 16);
        shapes.end();
    }

    Vector3 tp = new Vector3();
    boolean dragging;
    @Override
    public boolean mouseMoved (int screenX, int screenY) {
        Tools.debug("mouvement");
        // we can also handle mouse movement without anything pressed
		camera.unproject(tp.set(screenX, screenY, 0));
        return false;
    }

    @Override
    public boolean touchDown (int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override
    public boolean touchDragged (int screenX, int screenY, int pointer) {
        return true;
    }

    @Override
    public boolean touchUp (int screenX, int screenY, int pointer, int button) {
        return true;
    }

    @Override public void dispose () {
        // disposable stuff must be disposed
        shapes.dispose();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(this);
    }
}

/*
public class BreakOutScreen extends GlobalScreen implements InputProcessor {
    private BreakOut _breakOut=null;

    public BreakOutScreen(ScreenManager manager) {
        _manager=manager;
        _stage = new Stage();

        init();
    }

    private void init() {
        batch=new SpriteBatch();

        setupViewport(800, 600);
        _camera = new OrthographicCamera();
        _camera.setToOrtho(false, VIEWPORT.viewportWidth, VIEWPORT.viewportHeight);

        _multiplexer = new InputMultiplexer();
        _multiplexer.addProcessor(this);
        _multiplexer.addProcessor(_stage);
        Gdx.input.setInputProcessor(_multiplexer);

        //_breakOut=new BreakOut();
        //_breakOut.init();

        buildGame();

    }

    private void buildGame() {

    }

    public void update(float delta) {

    }


    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        update(delta);

        batch.setProjectionMatrix(_camera.combined);
        batch.begin();

        Gdx.gl.glClearColor(0.5f, 0.5f, 0.8f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        //_breakOut.render(batch);

        batch.end();

        _stage.act();
        _stage.draw();

    }

    @Override
    public void dispose() {
        _stage.clear();
        _stage.dispose();
    }

    @Override
    public boolean keyDown(int keycode) {
        Tools.debug("touche clavier down");
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        Tools.debug("touche clavier up");
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        Tools.debug("touchdown, position "+screenX+" / "+screenY);
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        Tools.debug("touchup position "+screenX+" / "+screenY);
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        Tools.debug("mouse moved position "+screenX+" / "+screenY);
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
*/