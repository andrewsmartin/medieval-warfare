package newworldorder.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;



import java.util.List;

import newworldorder.client.model.ModelController;
import newworldorder.client.shared.UIActionType;
import newworldorder.client.shared.UITileDescriptor;
import newworldorder.client.shared.ColourType;
//import newworldorder.game.model.Map;
import newworldorder.client.shared.StructureType;
import newworldorder.client.shared.TerrainType;
//import newworldorder.game.model.Tile;
import newworldorder.client.shared.UnitType;
import newworldorder.client.shared.VillageType;
public class TiledMapStage extends Stage {
	
	private TiledMap tiledMap;
	private final TiledMapDescriptors tiledMapDescriptors; 
	private TiledMapActor currentlyOutlined; 
	private boolean multiActionInput;
	private UIActionType whichMutiActionInput;
	private TiledMapActor previousActor;
	private ModelController model;
	private Skin skin = new Skin(Gdx.files.internal("skins/uiskin.json"));
	private UIStage uistage; 
	
	public TiledMapStage(TiledMap tiledMap, ModelController model, UIStage uistage) {

		this.multiActionInput = false;
		this.model = model;
		this.uistage = uistage; 

		//creating our tiledMapDescriptors for easy access when we update
		this.tiledMapDescriptors = new TiledMapDescriptors(tiledMap);
		this.tiledMap = tiledMap;
		System.out.println("Get stage height = " + this.getHeight());
		System.out.println("Get stage width = " + this.getWidth());
		
		TiledMapTileLayer tiledLayer = (TiledMapTileLayer) tiledMap.getLayers().get("colour");
		createActorsForLayer(tiledLayer);

	}  

	private void createActorsForLayer(TiledMapTileLayer tiledLayer) {
		for (int x = 0; x < tiledLayer.getWidth(); x++) {
			for (int y = 0; y < tiledLayer.getHeight(); y++) {
				TiledMapTileLayer.Cell cell = tiledLayer.getCell(x, y);
				TiledMapActor actor = new TiledMapActor(tiledMap, tiledLayer, cell,x,y);
				float setHeight; 
				if(x % 2 == 0){
					setHeight = y * tiledLayer.getTileHeight() + (tiledLayer.getTileHeight()*1/2);
				}
				else setHeight = y * tiledLayer.getTileHeight();
				float setWidth = x * tiledLayer.getTileWidth() *3/4 + (tiledLayer.getTileWidth() * 1/4);
				actor.setBounds(setWidth, setHeight, tiledLayer.getTileWidth()*1/2,
						tiledLayer.getTileHeight());
				addActor(actor);
				EventListener eventListener = new TiledMapClickListener(actor, skin);
				actor.addListener(eventListener);

			}
		}
	}

	public void tiledMapRenderUpdate (List<UITileDescriptor> UpdateTilesList) {
		UITileDescriptor currentTile; 
		int i = 0; 
		while (i < UpdateTilesList.size()) {
			currentTile = UpdateTilesList.get(i);
			i++;
			tiledMapColourUpdate(currentTile);
			tiledMapTerrainUpdate(currentTile);
			tiledMapUnitUpdate(currentTile);
			tiledMapStructureUpdate(currentTile);
			tiledMapVillageUpdate(currentTile);

		}
	}

	public void tiledMapColourUpdate(UITileDescriptor updatedDescription){
		int x = updatedDescription.x; 
		int y = updatedDescription.y;
		Cell updateCell = tiledMapDescriptors.colourLayer.getCell(x,y);

		ColourType newColour = updatedDescription.colourType;
		if (newColour != null){
			switch(newColour){


			case BLUE: updateCell.setTile(tiledMapDescriptors.blueTile); 
			break; 

			case RED: updateCell.setTile(tiledMapDescriptors.redTile);
			break;

			case GREEN: updateCell.setTile(tiledMapDescriptors.greenTile);
			break;

			case PURPLE: updateCell.setTile(tiledMapDescriptors.purpleTile);
			break;

			case ORANGE: updateCell.setTile(tiledMapDescriptors.orangeTile);
			break;

			case BROWN: updateCell.setTile(tiledMapDescriptors.brownTile);
			break;			

			case PINK: updateCell.setTile(tiledMapDescriptors.pinkTile);
			break;		

			case YELLOW: updateCell.setTile(tiledMapDescriptors.yellowTile);
			break;

			}
		}

		else{updateCell.setTile(tiledMapDescriptors.neutralTile); 


		}

	}

	public void tiledMapTerrainUpdate(UITileDescriptor updatedDescription){
		int x = updatedDescription.x;
		int y = updatedDescription.y;
		Cell updateCell = tiledMapDescriptors.terrainLayer.getCell(x,y);

		TerrainType newTerrain = updatedDescription.terrainType;
		if (newTerrain != null){

			switch(newTerrain){
			case MEADOW: updateCell.setTile(tiledMapDescriptors.meadowTile); 
			break; 

			case GRASS: updateCell.setTile(tiledMapDescriptors.grassTile);
			break;

			case SEA: updateCell.setTile(tiledMapDescriptors.oceanTile);
			break;

			case TREE: updateCell.setTile(tiledMapDescriptors.treeTile);
			break;
			}
		}
		else updateCell.setTile(tiledMapDescriptors.nullTile); 

	}

	public void tiledMapUnitUpdate(UITileDescriptor updatedDescription){
		int x = updatedDescription.x;
		int y = updatedDescription.y;
		Cell updateCell = tiledMapDescriptors.unitLayer.getCell(x,y);

		UnitType newUnit = updatedDescription.unitType;

		if(newUnit != null){
			switch(newUnit){
			case PEASANT: updateCell.setTile(tiledMapDescriptors.peasantTile);
			break;

			case INFANTRY: updateCell.setTile(tiledMapDescriptors.infantryTile);
			break;

			case SOLDIER: updateCell.setTile(tiledMapDescriptors.soldierTile);
			break;

			case KNIGHT: updateCell.setTile(tiledMapDescriptors.knightTile);
			break;
			
			case CANNON: updateCell.setTile(tiledMapDescriptors.cannonTile);
			break;
			}

		}
		else updateCell.setTile(tiledMapDescriptors.nullTile); 
	}
	
	public void tiledMapVillageUpdate(UITileDescriptor updatedDescription){
		int x = updatedDescription.x;
		int y = updatedDescription.y;
		Cell updateCell = tiledMapDescriptors.villageLayer.getCell(x,y);
		VillageType newVillage = updatedDescription.villageType;
		
		if(newVillage != null){
			switch(newVillage){
			case HOVEL: updateCell.setTile(tiledMapDescriptors.hovelTile);
			break;

			case TOWN: updateCell.setTile(tiledMapDescriptors.townTile);
			break;

			case FORT: updateCell.setTile(tiledMapDescriptors.fortTile);
			break;
			
			case CASTLE: updateCell.setTile(tiledMapDescriptors.castleTile);

			}

		}
		else updateCell.setTile(tiledMapDescriptors.nullTile); 
		
	}

	public void tiledMapStructureUpdate(UITileDescriptor updatedDescription){
		int x = updatedDescription.x;
		int y = updatedDescription.y;
		Cell updateCell = tiledMapDescriptors.structureLayer.getCell(x,y);

		StructureType newStructure = updatedDescription.structureType;
		if (newStructure != null){
			switch(newStructure){

			case ROAD: updateCell.setTile(tiledMapDescriptors.roadTile);
			break;

			case WATCHTOWER: updateCell.setTile(tiledMapDescriptors.towerTile);
			break;

			case TOMBSTONE: updateCell.setTile(tiledMapDescriptors.tombstoneTile);
			break;
			}
		}
		else updateCell.setTile(tiledMapDescriptors.nullTile); 
	}
	
	public boolean getMultiActionInput(){
		return this.multiActionInput;
	}

	public void setMultiActionInput(boolean bool){
		this.multiActionInput = bool;
	}

	public UIActionType getWhichMutiActionInput() {
		return whichMutiActionInput;
	}

	public void setWhichMutiActionInput(UIActionType whichMutiActionInput) {
		this.whichMutiActionInput = whichMutiActionInput;
	}

	public void setPreviousActor(TiledMapActor previousActor){
		this.previousActor = previousActor;

	}

	public TiledMapActor getPreviousActor(){
		return previousActor;
	}
	
	public TiledMapActor getCurrentlyOutlined(){
		return currentlyOutlined;
	}
	
	
	public void setCurrentlyOutlined(TiledMapActor currentlyOutlined){
		this.currentlyOutlined = currentlyOutlined;
	}
	
	public TiledMapDescriptors getTiledMapDescriptors(){
		return tiledMapDescriptors;
	}

	public ModelController getModel(){
		return model;
	}

	public UIStage getUIStage() {
		return this.uistage;
	}
}


