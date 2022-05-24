package com.lunion4.thegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lunion4.thegame.MuneGame;

public class GameOver implements Screen {
    int score = 0;
    int highscore;
    MuneGame game;
    Texture gameover,img_score,img_highscore;
    BitmapFont font;
    SpriteBatch spriteBatch;
    Texture again;
    boolean again_try;
    private static final int X = Gdx.graphics.getWidth()/8;
    private static final int Y = Gdx.graphics.getWidth()/5;

    public GameOver(MuneGame game, int score){
        this.game = game;
        this.score = score;
        gameover = new Texture("gameover.png");
        img_score = new Texture("score.png");
        img_highscore = new Texture("highscore.png");
        spriteBatch = new SpriteBatch();
        font = new BitmapFont(Gdx.files.internal("fonts/score.fnt"));
        font.getData().setScale(4,4);
        Preferences prefs = Gdx.app.getPreferences("score");
        this.highscore = prefs.getInteger("highscore", 0);
        again = new Texture("again.png");
        again_try = false;

        //Проверка на рекорд
        if (score > highscore) {
            prefs.putInteger("highscore", score);
            prefs.flush();
        }
    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        game.batch.begin();
        game.batch.draw(gameover, Gdx.graphics.getWidth()/2- gameover.getWidth()/2,Gdx.graphics.getHeight()-gameover.getHeight()*2);
        game.batch.draw(img_score, Gdx.graphics.getWidth()/2- img_score.getWidth()/2,(Gdx.graphics.getHeight()/3)*2);
        game.batch.draw(img_highscore, Gdx.graphics.getWidth()/2- img_highscore.getWidth()/2,(Gdx.graphics.getHeight()/3));

        font.draw(game.batch, "" + score,Gdx.graphics.getWidth()/2- img_score.getWidth()/2,(Gdx.graphics.getHeight()/3)*2-font.getCapHeight());
        font.draw(game.batch, "" + highscore,Gdx.graphics.getWidth()/2- img_highscore.getWidth()/2,(Gdx.graphics.getHeight()/3)-font.getCapHeight());
        game.batch.draw(again, X,Y, X*6, (again.getHeight()*3)/2);
        if (again_try) {
            game.setScreen(new MainGameScreen(game));
            this.dispose();
        }

        game.batch.end();
        again_try = Gdx.input.justTouched() && (Gdx.input.getX() > X) && (Gdx.input.getX() < X*7) && (Gdx.graphics.getHeight() - Gdx.input.getY() > Y) && (Gdx.graphics.getHeight() - Gdx.input.getY() < (Y + (again.getHeight()*3)/2));
    }

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
