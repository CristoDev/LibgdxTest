package com.mygdx.game.images;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public class CompositeSprite {

    Array<Sprite> componentSprites;

    public CompositeSprite() {
        componentSprites = new Array<Sprite>();
    }

    public void addComponentSprite(Sprite sprite) {
        componentSprites.add(sprite);
    }

    public void addComponentSprite(Sprite sprite, float x, float y) {
        sprite.setPosition(x, y);
        componentSprites.add(sprite);
    }

    // ... other methods left out for brevity

    public void draw(SpriteBatch batch) {
        for (Sprite sprite : componentSprites) {
            //sprite.setPosition(200,200);
            sprite.draw(batch);
        }
    }

}