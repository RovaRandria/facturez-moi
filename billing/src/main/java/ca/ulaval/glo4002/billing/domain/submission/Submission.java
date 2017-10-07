package ca.ulaval.glo4002.billing.domain.submission;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import ca.ulaval.glo4002.billing.domain.client.DueTerm;
import ca.ulaval.glo4002.billing.itemsManager.Cart;

public class Submission {

	@JsonSerialize
	private long clientId;
	@JsonSerialize
	private String creationDate;
	@JsonSerialize
	private Cart items;
	@JsonSerialize
	private DueTerm dueTerm;

	public Submission(long clientId, String creationDate, DueTerm dueTerm, Cart items) {
		this.clientId = clientId;
		this.creationDate = creationDate;
		this.items = items;
		this.dueTerm = dueTerm;
	}

	public Date setDate(String __date) {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
		try {
			return dateFormat.parse(__date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new Date();
		// courtesy to
		// stackoverflow.com/questions/35952809/java-convert-string-in-iso-instant-format-to-date
		// mais il doit exister de meilleures méthodes
	}
}
