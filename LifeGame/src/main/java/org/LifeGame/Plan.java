package org.LifeGame;

import java.util.ArrayList;

public class Plan {

	ArrayList<ArrayList<Cell>> surface;

	public Plan(int vert, int hor) {
		surface = new ArrayList<ArrayList<Cell>>();
		for (int i = 0; i < vert; i++) {
			surface.add(new ArrayList<Cell>());
			for (int j = 0; j < hor; j++) {
				surface.get(i).add(new Cell());
			}
		}
	}

	public Cell get(int i, int j) {
		return surface.get(i).get(j);
	}

	public void tourDeBoucle() {
		int vert = surface.size();
		int hor = surface.size();
		for (int i = 0; i < vert; i++) {
			for (int j = 0; j < hor; j++) {
				this.declareNeighboor(i, j);
			}
		}
		for (int i = 0; i < vert; i++) {
			for (int j = 0; j < hor; j++) {
				this.get(i, j).changeState();
			}
		}
	}

	private void declareNeighboor(int i, int j) {
		for (int k = i - 1; k < i + 2; k++) {
			for (int l = j - 1; l < j + 2; l++) {
				if (l > -1 && k > -1 && k < surface.size() && l < surface.get(0).size()) {
					if (this.get(k, l).isAlive())
						this.get(i, j).addNeighboor();
				}
			}
		}
	}

	public void afficher() {
		for (int i = 0; i < surface.size(); i++) {
			for (int j = 0; j < surface.get(0).size(); j++) {
				if (this.get(i, j).isAlive()) {
					System.out.print("o");
				} else {
					System.out.print("x");
				}
				System.out.print(" ");
			}
			System.out.println(" ");
		}
	}

}
