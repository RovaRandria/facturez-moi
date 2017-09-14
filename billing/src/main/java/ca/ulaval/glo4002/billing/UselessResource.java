package ca.ulaval.glo4002.billing;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/useless")
public class UselessResource {
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String FunctionThatReturnsAString() {
		List<String> stringList = new ArrayList<>();
		stringList.add("test1");
		stringList.add("test2");
		stringList.add("test3");
		ObjectMapper mapper = new ObjectMapper();
		String jsonInString = "";
		try {
			jsonInString = mapper.writeValueAsString(stringList);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonInString;
	}
}
