package ca.ulaval.glo4002.billing.domain;

public class IdClient {

	private static long indice = 0;

	public void update() {
		indice++;
	}

	public long next() {
		this.update();
		return indice;
	}

}
