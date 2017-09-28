package ca.ulaval.glo4002.billing.domain;

import java.math.BigDecimal;
import java.util.ArrayList;

import ca.ulaval.glo4002.billing.dto.DueTerm;

public class BillFactoryRepository {

	private static long indice = 1;

	private long idClient = 0;
	private BigDecimal total = new BigDecimal(0);
	private DueTerm dueTerm = DueTerm.IMMEDIATE;

	private ArrayList<Bill> listBill = new ArrayList<Bill>();

	public void setidClient(long idClient) {
		this.idClient = idClient;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public void setDueTerm(DueTerm dueTerm) {
		this.dueTerm = dueTerm;
	}

	public BillDto createBill() {
		indice++;
		if (total == null || dueTerm == null) {
			System.out.println("azrazrzef");
		}
		listBill.add(new Bill(indice, idClient, total, dueTerm));
		return new BillDto(indice, total, dueTerm);
	}

}
