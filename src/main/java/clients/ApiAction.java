package clients;

import org.testng.Assert;

import com.sun.jersey.api.client.ClientResponse;

import clients.models.BasicAuthResponse;
import core.BaseSetup;

public class ApiAction extends BaseSetup {

	public BasicAuthResponse getBasicPostman(String uri){
		ClientResponse resp = jerseyRestConsumer.doGetCall(uri, "application/json");
		Assert.assertEquals(resp.getStatus(), 200);
		return resp.getEntity(BasicAuthResponse.class);
	}

}