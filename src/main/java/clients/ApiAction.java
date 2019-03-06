package clients;

import javax.ws.rs.core.Response;

import org.testng.Assert;
import clients.models.response.BasicAuthResponse;
import core.BaseSetup;

public class ApiAction extends BaseSetup {
	public BasicAuthResponse getBasicPostman(String uri){
		Response resp = jerseyRestConsumer.doGetCall(uri, "application/json");
		Assert.assertEquals(resp.getStatus(), 200);
		return resp.readEntity(BasicAuthResponse.class);
	}
}