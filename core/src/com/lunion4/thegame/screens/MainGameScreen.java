package com.lunion4.thegame.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.lunion4.thegame.MuneGame;
import com.lunion4.thegame.scripts.Asteroid;
import com.lunion4.thegame.scripts.Bullet;
import com.lunion4.thegame.scripts.Collision;

import java.util.ArrayList;
import java.util.Random;


public class MainGameScreen implements Screen {
    public static final float SPEED = 120;
    MuneGame game;
    int score;
    int health; //3 сердечка здоровья. При 0 сердечек конец игры.
    Collision player_collision;
    public static final int SHIP_W_PIX = 13;
    public static final int SHIP_H_PIX = 19;
    public static final int SHIP_W = SHIP_W_PIX * 12;
    public static final int SHIP_H = SHIP_H_PIX * 12;
    public static final int HEART = 13*10;
    float x,y;
    Texture ship;
    Texture h1,h2,h3;
    Random random;
    float SpawnAsteroid;
    ArrayList<Asteroid> asteroids;
    ArrayList<Bullet> bullets;
    BitmapFont scoreFont;

    public MainGameScreen(MuneGame game) {
        this.game = game;
        score = 0;
        health = 3;
        y = 100;
        x = Gdx.graphics.getWidth() / 2 - SHIP_W / 2;
        player_collision = new Collision(0,0,SHIP_W,SHIP_H);
        ship = new Texture("ship.png");
        h1 = new Texture("heart.png");
        h2 = new Texture("heart.png");
        h3 = new Texture("heart.png");
        random = new Random();
        SpawnAsteroid = random.nextFloat() * 0.01f + 0.05f;
        asteroids = new ArrayList<Asteroid>();
        bullets = new ArrayList<Bullet>();
        scoreFont = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        scoreFont.getData().setScale(2,2);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {

        //Spawn Asteroid
        SpawnAsteroid -= delta;
        if (SpawnAsteroid <= 0){
            SpawnAsteroid = random.nextFloat() * 0.01f + 0.05f;
            asteroids.add(new Asteroid(random.nextInt(Gdx.graphics.getHeight()-Asteroid.HEIGHT)));
        }
        //Update Asteroid
        ArrayList<Asteroid> removedAsteroid = new ArrayList<Asteroid>();
        for (Asteroid asteroid : asteroids) {
            asteroid.update(delta);
            if (asteroid.remove)
                removedAsteroid.add(asteroid);
        }
        //Стрельба
        if (shoot()){
            bullets.add(new Bullet(x + 4));
            bullets.add(new Bullet(x + SHIP_W - 4));
        }
        //Update Bullet
        ArrayList<Bullet> removedBullet = new ArrayList<Bullet>();
        for (Bullet bullet : bullets) {
            bullet.update(delta);
            if (bullet.remove)
                removedBullet.add(bullet);
        }
        //Движение корабля
        if (isRight() && x < Gdx.graphics.getWidth()-SHIP_W){x=x+Gdx.graphics.getWidth()/256;} else if (isLeft() && x > 0){x=x-Gdx.graphics.getWidth()/256;}
        player_collision.move(x,y);

        for (Bullet bullet : bullets) {
            for (Asteroid asteroid : asteroids) {
                if (bullet.getCollisionRect().collidesWith(asteroid.getCollisionRect())) {//Collision occured
                    removedBullet.add(bullet);
                    removedAsteroid.add(asteroid);
                    score += 100;
                }
            }
        }
        bullets.removeAll(removedBullet);

        for (Asteroid asteroid : asteroids) {
            if (asteroid.getCollisionRect().collidesWith(player_collision)) {
                removedAsteroid.add(asteroid);
                health -= 1;
            }
        }
        asteroids.removeAll(removedAsteroid);


        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.scrollingBackground.update(delta, game.batch);
        game.batch.draw(ship,x,y,SHIP_W,SHIP_H);

        for (Bullet bullet : bullets) {
            bullet.render(game.batch);
        }

        for (Asteroid asteroid : asteroids) {
            asteroid.render(game.batch);
        }
        //Проверка на здоровье
        if (health==3){
            game.batch.draw(h1,Gdx.graphics.getWidth()/32,Gdx.graphics.getHeight()-HEART,HEART,HEART);
            game.batch.draw(h2,Gdx.graphics.getWidth()/24+HEART,Gdx.graphics.getHeight()-HEART,HEART,HEART);
            game.batch.draw(h3,Gdx.graphics.getWidth()/18+2*HEART,Gdx.graphics.getHeight()-HEART,HEART,HEART);
        }
        else if (health == 2){
            game.batch.draw(h1,Gdx.graphics.getWidth()/32,Gdx.graphics.getHeight()-HEART,HEART,HEART);
            game.batch.draw(h2,Gdx.graphics.getWidth()/24+HEART,Gdx.graphics.getHeight()-HEART,HEART,HEART);
        }
        else if (health == 1) {
            game.batch.draw(h1, Gdx.graphics.getWidth() / 32, Gdx.graphics.getHeight() - HEART, HEART, HEART);
        } else{
            this.dispose();
            game.setScreen(new GameOver(game,score));
        }
        GlyphLayout scoreLayout = new GlyphLayout(scoreFont, "" + score);
        scoreFont.draw(game.batch, scoreLayout, Gdx.graphics.getWidth() / 2 - scoreLayout.width / 2, Gdx.graphics.getHeight() - scoreLayout.height - 10);
        game.batch.end();
    }
    boolean isRight(){return Gdx.input.isTouched() && Gdx.input.getX() >= Gdx.graphics.getWidth()/2 && Gdx.input.getY()>(Gdx.graphics.getHeight()/3)*2;}
    boolean isLeft(){return Gdx.input.isTouched() && Gdx.input.getX() < Gdx.graphics.getWidth()/2 && Gdx.input.getY()>(Gdx.graphics.getHeight()/3)*2;}
    boolean shoot(){return Gdx.input.isTouched() && Gdx.input.getY()<=(Gdx.graphics.getHeight()/3)*2;}
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
