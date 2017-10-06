package org.LifeGame;

import java.util.ArrayList;
import java.util.Iterator;

public class Game {

	private int vert = 0;
	private int hor = 0;
	private ArrayList<int[]> positions;
	private int generations = 0;
	private Plan plan;

	public Game(int __vert, int __hor, ArrayList<int[]> __positions, int __generations) {

		if (!__positions.isEmpty()) {
			Iterator<int[]> itPositions = __positions.iterator();
			int[] depart = itPositions.next();
			int vertTemp = depart[0];
			int horTemp = depart[1];
			while (itPositions.hasNext()) {
				int[] boucle = itPositions.next();
				vertTemp = ((vertTemp > boucle[0])) ? vertTemp : boucle[0];
				horTemp = ((horTemp > boucle[1])) ? horTemp : boucle[1];
			}
			if (vertTemp < __vert || horTemp < __hor) {
				vert = __vert;
				hor = __hor;
				positions = __positions;
				generations = __generations;
			}
		} else {
			vert = __vert;
			hor = __hor;
			positions = __positions;
			generations = __generations;
		}
		this.plan = new Plan(__vert, __hor);
	}

	public boolean isStarted() {
		if (this.vert != 0 && hor != 0 && positions != null && generations != 0 && this.plan != null) {
			return true;
		} else {
			return false;
		}
	}

	public void play() {
		while (this.generations > 0) {
			this.plan.tourDeBoucle();
		}
	}

	public void afficher() {
		this.plan.afficher();
	}

}
