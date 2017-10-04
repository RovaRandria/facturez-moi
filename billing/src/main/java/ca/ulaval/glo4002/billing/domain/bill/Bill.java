package ca.ulaval.glo4002.billing.domain;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.dto.DueTerm;
import itemsManager.Cart;

public class Bill {

	@JsonSerialize
	private long id;
	@JsonSerialize
	private long idClient;
	@JsonSerialize
	private Date date;
	@JsonSerialize
	private Cart cart;
	@JsonSerialize
	private DueTerm dueTerm;
	@JsonSerialize
	private String url;

	public Bill(long id, long idClient, Date date, Cart itemRepository, DueTerm dueTerm) {
		this.id = id;
		this.idClient = idClient;
		this.date = date;
		this.cart = itemRepository;
		this.dueTerm = dueTerm;
		this.url = "/bills/" + id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setIdClient(long idClient) {
		this.idClient = idClient;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public void setItemRepository(Cart cart) {
		this.cart = cart;
	}

	public void setDueTerm(DueTerm dueTerm) {
		this.dueTerm = dueTerm;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
