package com.lunion4.thegame.scripts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Bullet {

    public static final int SPEED = 700;
    public static final int DEFAULT_Y = 320;
    public static final int WIDTH = 3*5;
    public static final int HEIGHT = 12*5;
    private static Texture texture;

    float x, y;
    Collision rect;
    public boolean remove = false;

    public Bullet(float x) {
        this.x = x;
        this.y = DEFAULT_Y;
        this.rect = new Collision(x, y, WIDTH, HEIGHT);

        if (texture == null)
            texture = new Texture("bullet.png");
    }

    public void update (float deltaTime) {
        y += SPEED * deltaTime;
        if (y > Gdx.graphics.getHeight())
            remove = true;

        rect.move(x, y);
    }

    public void render (SpriteBatch batch) {
        batch.draw(texture, x, y,WIDTH,HEIGHT);
    }

    public Collision getCollisionRect() {
        return rect;
    }

}