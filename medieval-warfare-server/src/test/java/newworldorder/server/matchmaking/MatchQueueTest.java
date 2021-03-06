package newworldorder.server.matchmaking;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MatchQueueTest {
	private int numPlayers = 3;
	private MatchQueue queue;
	
	@Before
	public void setup() {
		queue = new MatchQueue(numPlayers);
	}
	
	@After
	public void tearDown() {
		queue = null;
	}
	
	@Test
	public void testCreateMatchQueue() {
		assertEquals(queue.getNumPlayers(), numPlayers);
		assertFalse(queue.hasGame());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testEmptyQueuePopThrowsException() {
		queue.popGame();
	}
	
	@Test
	public void testInsertPlayer() {
		queue.insertPlayer("player1");
		assertEquals(1, queue.size());
	}
	
	@Test(expected=NoSuchElementException.class)
	public void testPartialQueuePopThrowsException() {
		queue.insertPlayer("player1");
		queue.insertPlayer("player2");
		queue.popGame();
	}
	
	@Test
	public void testQueueWithGamePops() {
		List<String> expected = new ArrayList<>();
		expected.add("player1");
		expected.add("player2");
		expected.add("player3");
		
		queue.insertPlayer("player1");
		queue.insertPlayer("player2");
		queue.insertPlayer("player3");
		
		List<String> actual = queue.popGame();
		
		assertEquals(expected, actual);
	}
	
}
