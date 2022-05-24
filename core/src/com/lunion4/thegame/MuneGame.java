package com.lunion4.thegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lunion4.thegame.screens.GameOver;
import com.lunion4.thegame.screens.MainMenu;
import com.lunion4.thegame.scripts.ScrollingBackground;

public class MuneGame extends Game {

	public SpriteBatch batch;
	public ScrollingBackground scrollingBackground;

	@Override
	public void create () {
		batch = new SpriteBatch();


		this.scrollingBackground = new ScrollingBackground();
		this.setScreen(new MainMenu(this));
		//this.setScreen(new GameOver(this,1000));
	}

	@Override
	public void render () {
		super.render();
	}
}
