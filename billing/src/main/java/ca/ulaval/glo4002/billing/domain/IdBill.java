package ca.ulaval.glo4002.billing.domain;

public class IdBill {

	private static long indice = 0;

	public void update() {
		indice++;
	}

	public long next() {
		this.update();
		return indice;
	}

}
