package newworldorder.game;

import com.badlogic.gdx.Game;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;

import newworldorder.client.model.ModelController;

public class MedievalWarfareGame extends Game {
	public TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	SpriteBatch sb;
	Sprite sprite;
	Texture texture;
	OrthographicCamera camera;
	GameScreen gameScreen;
	LoginScreen loginScreen;
	MatchmakingScreen matchmakingScreen;

	@Override
	public void create () {

	gameScreen = new GameScreen();
	loginScreen =new LoginScreen(this);
	matchmakingScreen = new MatchmakingScreen();
	this.setScreen(matchmakingScreen);
//	this.setScreen(loginScreen);
//	this.setScreen(gameScreen);
//	this.setScreen(loginScreen);
	}
	
	public MedievalWarfareGame() {
		super();
	}

	@Override
	public void render () {
		super.render();
	}
	
	public void setGameScreen() {
		this.setScreen(new GameScreen());
	}
	
	public void setLoginScreen() {
		this.setScreen(new LoginScreen(this));
	}
}
