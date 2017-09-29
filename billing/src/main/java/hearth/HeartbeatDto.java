package hearth;

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

	public String GetToken() {
		return token;
	}

	public void SetToken(String token) {
		this.token = token;
	}

	public long GetDate() {
		return date;
	}

	public void SetDate(long date) {
		this.date = date;
	}
}
