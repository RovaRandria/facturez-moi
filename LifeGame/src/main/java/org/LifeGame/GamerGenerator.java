package org.LifeGame;

import java.util.ArrayList;
import java.util.Scanner;

public class GamerGenerator {

	private int vert = 0;
	private int hor = 0;
	private ArrayList<int[]> positions = new ArrayList<int[]>();
	private int generations = 0;

	public void grid(String entree) {
		Scanner scanner = new Scanner(entree);
		this.vert = scanner.useDelimiter("\\D").nextInt();
		this.hor = scanner.useDelimiter("\\D").nextInt();
		scanner.close();
	}

	public void generations(String entree) {
		this.generations = Integer.parseInt(entree);

	}

	public void positions(String entree) {

		Scanner scanner = new Scanner(entree);
		while (scanner.hasNext()) {
			int[] position = { 0, 0 };
			position[0] = scanner.useDelimiter("\\D").nextInt();
			position[1] = scanner.useDelimiter("\\D").nextInt();
			this.positions.add(position);
		}

		scanner.close();

	}

	public int[] getGrid() {
		int[] grid = { 0, 0, 0 };
		grid[0] = this.vert;
		grid[1] = this.hor;
		grid[2] = this.generations;
		return grid;
	}

	public ArrayList<int[]> getPositions() {
		return positions;
	}

	public Game createGame() {
		return new Game(this.vert, this.hor, this.positions, this.generations);
	}

}
