package com.lunion4.thegame.scripts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingBackground {
    Texture img;
    float y0,y1;
    float imgScale;
    int speed;
    public ScrollingBackground(){
        img = new Texture("stars_background.png");
        y0 = 0;
        y1 = img.getHeight();
        imgScale = Gdx.graphics.getWidth() / img.getWidth();
        speed = 250; // скорость прокрутки пискселей в секунду

    } //переход нижней части вверх
    public void update(float deltaTime, SpriteBatch batch){
        y0 -= speed * deltaTime;
        y1 -= speed * deltaTime;
        if (y0 +img.getHeight() * imgScale <= 0){y0 = y1 + img.getHeight() * imgScale;}
        if (y1 +img.getHeight() * imgScale <= 0){y1 = y0 + img.getHeight() * imgScale;}
        batch.draw(img,0,y0,Gdx.graphics.getWidth(),img.getHeight()*imgScale);
        batch.draw(img,0,y1,Gdx.graphics.getWidth(),img.getHeight()*imgScale);
    }
}
