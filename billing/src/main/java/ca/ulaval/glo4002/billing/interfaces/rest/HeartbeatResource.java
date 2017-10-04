package hearth;

import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

public class HeartbeatResource {

	@Path("/heartbeat")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public HeartbeatDto GetHeartbeat(@QueryParam("token") String token) {
		long timestamp = new Date().toInstant().toEpochMilli();
		return new HeartbeatDto(timestamp, token);
	}
}
