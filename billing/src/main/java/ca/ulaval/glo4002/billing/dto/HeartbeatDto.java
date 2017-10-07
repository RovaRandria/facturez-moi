package ca.ulaval.glo4002.billing.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class HeartbeatDto {

	@JsonSerialize
	private long date;
	@JsonSerialize
	private String token;

	public HeartbeatDto() {
	}

	public HeartbeatDto(long timestamp, String token) {
		SetToken(token);
		SetDate(timestamp);
	}

	public void SetToken(String token) {
		this.token = token;
	}

	public void SetDate(long date) {
		this.date = date;
	}
}
