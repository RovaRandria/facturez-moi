package Game;

import org.LifeGame.Game;
import org.LifeGame.GamerGenerator;
import org.LifeGame.SystemGetters;

public class ServerLauncher implements Runnable {
	public static void main(String[] args) {
		new ServerLauncher().run();
	}

	public void run() {

		GamerGenerator gamerGenerator = new GamerGenerator();
		SystemGetters systemGetters = new SystemGetters();

		gamerGenerator.grid(systemGetters.getGrid());
		gamerGenerator.positions(systemGetters.getPositions());
		gamerGenerator.generations(systemGetters.getGenerations());

		Game game = gamerGenerator.createGame();

		game.play();

		game.afficher();

	}
}