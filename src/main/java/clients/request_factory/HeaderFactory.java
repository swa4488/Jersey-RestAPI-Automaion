package clients.request_factory;

import java.util.HashMap;

public class HeaderFactory {
	public static final String CONTENT_TYPE = "Content-Type";
	public static final String APPLICATION_JSON = "application/json";

	public static HashMap<String, String> defaultHeader() {
		HashMap<String, String> headers = new HashMap<String, String>();
		headers.put(CONTENT_TYPE, APPLICATION_JSON);
		return headers;
	}
}