package org.LifeGame;

public class Cell {

	private boolean stayAlive = false;
	private int neighboor = 0;

	public boolean isAlive() {
		return stayAlive;
	}

	public void kill() {
		stayAlive = false;
	}

	public void makeAlive() {
		stayAlive = true;
	}

	public void addNeighboor() {
		neighboor++;
	}

	public void changeState() {
		if (neighboor < 2) {
			this.kill();
		} else if (neighboor == 2) {
			//
		} else if (neighboor == 3) {
			this.makeAlive();
		} else {
			this.kill();
		}
	}

}
