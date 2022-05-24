package com.lunion4.thegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.lunion4.thegame.MuneGame;


public class MainMenu implements Screen {
    MuneGame game;
    Texture background;
    Texture moon;
    Texture begin;
    public static final int MOON_WIDTH = Gdx.graphics.getWidth();
    private static final int X = Gdx.graphics.getWidth()/8;
    private static final int Y = Gdx.graphics.getWidth()/3;
    public MainMenu(MuneGame game){
        this.game = game;
        background = new Texture("background_ver.png");
        moon = new Texture("moon.png");
        begin = new Texture("begin.png");
    }
    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        game.batch.begin();
        game.batch.draw(background, 0,0, Gdx.graphics.getWidth(),Gdx.graphics.getHeight()); //звездный фон
        game.batch.draw(moon,0,Gdx.graphics.getHeight()/3,MOON_WIDTH,MOON_WIDTH); //луна с названием игры
        game.batch.draw(begin,X,Y,X * 6,(begin.getHeight()*3)/2); //кнопка "начать"
        if (Gdx.input.getX() > X && Gdx.input.getX() < (X*7) && Gdx.graphics.getHeight() - Gdx.input.getY() < Y +(begin.getHeight()*3)/2 && Gdx.graphics.getHeight() - Gdx.input.getY() > Y   ){
            if (Gdx.input.isTouched()){

                game.setScreen(new MainGameScreen(game));}
                this.dispose();
        }
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
