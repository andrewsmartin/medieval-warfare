package newworldorder.client.model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class GameEngineTest {
	private Map aMap;
	private Village village1, village2;
	private Player p1, p2;
	private GameEngine gameEngine;

	@Before
	public void setUp() {
		p1 = new Player("Yung", "Lean", 0, 0, null);
		p2 = new Player("2", "Chainz", 0, 0, null);
		List<Player> playerList = new ArrayList<Player>();
		playerList.add(p1);
		playerList.add(p2);
		aMap = new Map(10, 10);
		Game aGame = new Game(playerList, aMap);
		gameEngine = new GameEngine(aGame);
		p1.setCurrentGame(aGame);
		p2.setCurrentGame(aGame);
		aGame.setTurnOf(p1);
		List<Tile> reg1 = new ArrayList<Tile>();
		reg1.add(aMap.getTile(0, 0));
		reg1.add(aMap.getTile(1, 0));
		reg1.add(aMap.getTile(0, 1));
		village1 = new Village(aMap.getTile(0, 0), p1, reg1);

		List<Tile> reg2 = new ArrayList<Tile>();
		reg2.add(aMap.getTile(1, 1));
		reg2.add(aMap.getTile(2, 1));
		reg2.add(aMap.getTile(1, 2));
		reg2.add(aMap.getTile(2, 2));
		village2 = new Village(aMap.getTile(2, 2), p2, reg2);
	}

	@Test
	public void testBuildRoad() {
		Unit aUnit = new Unit(UnitType.PEASANT, village1, aMap.getTile(1, 0));
		assertFalse(aMap.getTile(1, 0).getStructure() == StructureType.ROAD);
		gameEngine.buildRoad(aUnit);
		assertTrue(aMap.getTile(1, 0).getStructure() == StructureType.ROAD);

		Unit aUnit2 = new Unit(UnitType.KNIGHT, village1, aMap.getTile(0, 1));
		assertFalse(aMap.getTile(0, 1).getStructure() == StructureType.ROAD);
		gameEngine.buildRoad(aUnit2);
		assertFalse(aMap.getTile(0, 1).getStructure() == StructureType.ROAD);
	}

	@Test
	public void testTakeoverTile() {
		// TODO fail("Not yet implemented");
	}

	@Test
	public void testNewGame() {
		// TODO fail("Not yet implemented");
	}

	@Test
	public void testBeginTurn() {
		// TODO fail("Not yet implemented");
	}

	@Test
	public void testUpgradeUnit() {
		// TODO fail("Not yet implemented");
	}

	@Test
	public void testUpgradeVillage() {
		// TODO fail("Not yet implemented");
	}

	@Test
	public void testBuildTower() {
		// TODO fail("Not yet implemented");
	}

	@Test
	public void testGetGameState() {
		// TODO fail("Not yet implemented");
	}

	@Test
	public void testSetGameState() {
		// TODO fail("Not yet implemented");
	}

	@Test
	public void testMoveUnit() {
		// TODO fail("Not yet implemented");
	}

}
