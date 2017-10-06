package org.LifeGame;

import org.junit.Test;
import org.mockito.Mockito;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestGameGenerator extends TestCase {
	SystemGetters mock;

	@Test
	public void test_convertStringToGridAndGenerations() {
		mock = Mockito.mock(SystemGetters.class);
		Mockito.when(mock.getGrid()).thenReturn("4x5");
		Mockito.when(mock.getPositions()).thenReturn("1,2;1,5;");
		Mockito.when(mock.getGenerations()).thenReturn("2");

		GamerGenerator converter = new GamerGenerator();

		converter.grid(mock.getGrid());
		converter.generations(mock.getGenerations());

		Assert.assertEquals(4, converter.getGrid()[0]);
		Assert.assertEquals(5, converter.getGrid()[1]);
		Assert.assertEquals(2, converter.getGrid()[2]);
	}

	@Test
	public void test_convertPositions() {
		mock = Mockito.mock(SystemGetters.class);
		Mockito.when(mock.getGrid()).thenReturn("4x5");
		Mockito.when(mock.getPositions()).thenReturn("1,2;1,5;");
		Mockito.when(mock.getGenerations()).thenReturn("2");

		GamerGenerator converter = new GamerGenerator();

		converter.positions(mock.getPositions());

		Assert.assertEquals(1, converter.getPositions().get(0)[0]);
		Assert.assertEquals(2, converter.getPositions().get(0)[1]);
		Assert.assertEquals(1, converter.getPositions().get(1)[0]);
		Assert.assertEquals(5, converter.getPositions().get(1)[1]);
	}

	@Test
	public void test_generateGame() {
		mock = Mockito.mock(SystemGetters.class);
		Mockito.when(mock.getGrid()).thenReturn("4x5");
		Mockito.when(mock.getPositions()).thenReturn("1,2;1,5;");
		Mockito.when(mock.getGenerations()).thenReturn("2");

		GamerGenerator gameGenerator = new GamerGenerator();

		gameGenerator.grid(mock.getGrid());
		gameGenerator.generations(mock.getGenerations());
		gameGenerator.positions(mock.getPositions());

		Game game = gameGenerator.createGame();

		Assert.assertEquals(true, game.isStarted());
	}
}
