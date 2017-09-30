package ca.ulaval.glo4002.billing.domain;

public class IdBill {

	private static long index = 0;

	public void update() {
		index++;
	}

	public long next() {
		this.update();
		return index;
	}

}
