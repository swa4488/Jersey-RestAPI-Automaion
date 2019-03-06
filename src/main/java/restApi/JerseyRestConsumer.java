package restApi;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import configuration.Configuration;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

public class JerseyRestConsumer {

	private static Logger logger = LoggerFactory.getLogger(JerseyRestConsumer.class);
	protected Configuration config = null;
	protected Client client = null;
	protected MediaType responseType = null;

	public JerseyRestConsumer() {
		client = ClientBuilder.newClient( new ClientConfig().register(JacksonJsonProvider.class));
		config = new Configuration();
	}

	public URI constructURI(String path, Map<String, String> queryParams) {
		UriBuilder uriBuilder = UriBuilder.fromPath(path);
		if(queryParams != null) {
			for(Map.Entry<String, String> e : queryParams.entrySet()) {
				uriBuilder.queryParam(e.getKey(), e.getValue());
			}
		}
		return uriBuilder.build();

	}

	public Response buildRequest(String uri, String body) {
		WebTarget webTarget = client.target(uri);
		return webTarget.request(MediaType.APPLICATION_JSON).post(Entity.entity(body, MediaType.APPLICATION_JSON));
	}

	public Response doPost(JerseyRequest request) {
		WebTarget webTarget = client.target(request.getUrl());
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.post(Entity.entity(request.getBody(), MediaType.APPLICATION_JSON));
		if (response.getStatus() < 200 || response.getStatus() >= 300) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		return response;
	}


	public Response doGetCall(String requestURL, String contentType) {
		HttpAuthenticationFeature feature = HttpAuthenticationFeature.basic(config.getConfig("username"), 
				config.getConfig("password"));
		client.register(feature);
		logger.info("Hitting url: " + requestURL);
		WebTarget webTarget = client.target(config.getConfig("baseURL") + requestURL);
		Invocation.Builder invocationBuilder =  webTarget.request(contentType);
		return invocationBuilder.get();
	}

	public Response doPostCall(String requestURL, String contentType, String body) {
		WebTarget webTarget = client.target(requestURL);
		Invocation.Builder invocationBuilder =  webTarget.request(MediaType.APPLICATION_JSON);
		Response response = null;
		logger.info("Hitting url: " + requestURL + " with body: " + body);
		response = invocationBuilder.post(Entity.entity(body, contentType));
		return response;
	}
}
