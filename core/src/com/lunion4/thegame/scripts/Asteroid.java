package com.lunion4.thegame.scripts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Asteroid {

    public static final int SPEED = 250;
    public static final int WIDTH = 14*5;
    public static final int HEIGHT = 14*5;
    private static Texture texture;

    float x, y;
    Collision rect;
    public boolean remove = false;

    public Asteroid (float x) {
        this.x = x;
        this.y = Gdx.graphics.getHeight();
        this.rect = new Collision(x, y, WIDTH, HEIGHT);

        if(texture == null) {texture = new Texture("asteroid.png");}
    }

    public void update(float deltaTime) {
        y -= SPEED * deltaTime;
        if (y < -HEIGHT)
            remove = true;

        rect.move(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, WIDTH, HEIGHT);
    }

    public Collision getCollisionRect() {
        return rect;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

}

