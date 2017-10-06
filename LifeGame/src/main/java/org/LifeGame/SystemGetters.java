package org.LifeGame;

public class SystemGetters {

	public String getGrid() {
		return System.getProperty("grid");
	}

	public String getPositions() {
		return System.getProperty("liveCells");
	}

	public String getGenerations() {
		return System.getProperty("generations");
	}

}
