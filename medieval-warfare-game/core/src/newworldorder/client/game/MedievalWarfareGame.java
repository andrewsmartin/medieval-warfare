package newworldorder.client.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TiledMapTile;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileSet;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.HexagonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class MedievalWarfareGame extends ApplicationAdapter implements InputProcessor {
	SpriteBatch sb;
	Sprite sprite;
	Texture texture;
	TiledMap tiledMap;
	OrthographicCamera camera;
	MapLayer objectLayer;

	TiledMapRenderer tiledMapRenderer;

	TextureRegion textureRegion;

	@Override
	public void create() {
		// batch = new SpriteBatch();

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);
		camera.update();

		tiledMap = new TmxMapLoader().load("lunar.tmx");
		MapProperties prop = tiledMap.getProperties();
		int mapWidth = prop.get("width", Integer.class);
		int mapHeight = prop.get("height", Integer.class);
		int tilePixelWidth = prop.get("tilewidth", Integer.class); // size of
																	// each tile
		int tilePixelHeight = prop.get("tileheight", Integer.class);
		int viewPortHeight = tilePixelHeight * 10 * 3 / 4;

		tiledMapRenderer = new HexagonalTiledMapRenderer(tiledMap);
		Gdx.input.setInputProcessor(this);

		sb = new SpriteBatch();

		texture = new Texture(Gdx.files.internal("pik.png"));
		sprite = new Sprite(texture);

		Stage stage = new TiledMapStage(tiledMap);
		Gdx.input.setInputProcessor(stage);
		/*
		 * objectLayer = tiledMap.getLayers().get("objects"); textureRegion =
		 * new TextureRegion(texture,64,64);
		 * 
		 * TextureMapObject tmo = new TextureMapObject(textureRegion);
		 * tmo.setX(0); tmo.setY(0); objectLayer.getObjects().add(tmo);
		 */

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		camera.update();
		tiledMapRenderer.setView(camera);
		tiledMapRenderer.render();
		sb.setProjectionMatrix(camera.combined);

		sb.begin();
		sprite.draw(sb);
		sb.end();
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.LEFT)
			camera.translate(-32, 0);
		if (keycode == Input.Keys.RIGHT)
			camera.translate(32, 0);
		if (keycode == Input.Keys.DOWN)
			camera.translate(0, -32);
		if (keycode == Input.Keys.UP)
			camera.translate(0, 32);
		if (keycode == Input.Keys.NUM_1)
			tiledMap.getLayers().get(0).setVisible(!tiledMap.getLayers().get(0).isVisible());
		if (keycode == Input.Keys.NUM_2)
			tiledMap.getLayers().get(1).setVisible(!tiledMap.getLayers().get(1).isVisible());
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {

		TiledMapTileLayer layer = (TiledMapTileLayer) tiledMap.getLayers().get("base");
		System.out.println(layer);
		TiledMapTileLayer.Cell cell = layer.getCell(0, 0);
		System.out.println(cell.getTile());

		TiledMapTileSet tileset = tiledMap.getTileSets().getTileSet("heximage");
		/* added code for practice */
		Vector3 touch = new Vector3(screenX, screenY, 0);

		TiledMapTile brown = tileset.getTile(2);

		cell.setTile(brown);

		return true;

	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
