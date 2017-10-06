package org.LifeGame;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;
import junit.framework.TestCase;

public class TestLaunch extends TestCase {

	@Test
	public void test_instanciate_game() {
		ArrayList<int[]> positions = new ArrayList<int[]>();

		int[] position1 = { 1, 2 };
		int[] position2 = { 1, 2 };
		positions.add(position1);
		positions.add(position2);

		int vert = 4;
		int hor = 5;

		int generations = 2;

		Game game = new Game(vert, hor, positions, generations);

		Assert.assertEquals(true, game.isStarted());
	}

	@Test
	public void test_instanciateGame_withOutOfRangePositions_mustFail() {
		ArrayList<int[]> positions = new ArrayList<int[]>();

		int[] position1 = { 6, 7 };
		positions.add(position1);

		int vert = 4;
		int hor = 5;

		int generations = 2;

		Game game = new Game(vert, hor, positions, generations);

		Assert.assertEquals(false, game.isStarted());
	}

	@Test
	public void test_instanciateGames_withNullParameters_mustFail() {
		ArrayList<int[]> positions = new ArrayList<int[]>();

		int vert = 0;
		int hor = 5;
		int generations = 2;
		Game game = new Game(vert, hor, positions, generations);

		vert = 5;
		hor = 0;
		generations = 2;
		Game game2 = new Game(vert, hor, positions, generations);

		vert = 5;
		hor = 5;
		generations = 0;
		Game game3 = new Game(vert, hor, positions, generations);

		Assert.assertEquals(false, game.isStarted());
		Assert.assertEquals(false, game2.isStarted());
		Assert.assertEquals(false, game3.isStarted());

	}

}
