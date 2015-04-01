package newworldorder.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Tree;
import com.badlogic.gdx.scenes.scene2d.ui.Tree.Node;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import newworldorder.client.model.ModelController;

public class MedievalWarfareGame extends Game {
	public TiledMap tiledMap;
	TiledMapRenderer tiledMapRenderer;
	SpriteBatch sb;
	Sprite sprite;
	Texture texture;
	OrthographicCamera camera;
	GameScreen gameScreen;
	private ModelController model = ModelController.getInstance();

	@Override
	public void create () {

		
		tiledMap = new TmxMapLoader().load("./map/blankMap.tmx");
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		tiledMapRenderer = new HexagonalTiledMapRenderer(tiledMap);
		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);		

		TiledMapStage stage = new TiledMapStage(tiledMap,model);
		gameScreen = new GameScreen(this, tiledMapRenderer, stage, camera);
	this.setScreen(new LoginScreen(gameScreen, this));
		//this.setScreen(gameScreen);
		
	}

	@Override
	public void render () {
		super.render();
	}
	
	public ModelController getModel(){
		return model;
	}
}