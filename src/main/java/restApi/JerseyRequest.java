package restApi;

import java.util.HashMap;

import lombok.Data;

@Data
public class JerseyRequest {

  private String url;
  private String body;
  private String type;
  private HashMap<String, String> headers = new HashMap<String, String>();
  private Method method;

  public JerseyRequest(JerseyRequestBuilder jerseyRequestBuilder) {

    this.setUrl(jerseyRequestBuilder.url);
    this.setBody(jerseyRequestBuilder.body);
    this.setType(jerseyRequestBuilder.type);
    this.setHeaders(jerseyRequestBuilder.headers);
    this.setMethod(jerseyRequestBuilder.method);

  }

  public String getUrl() {
	return url;
}

public void setUrl(String url) {
	this.url = url;
}

public String getBody() {
	return body;
}

public void setBody(String body) {
	this.body = body;
}

public String getType() {
	return type;
}

public void setType(String type) {
	this.type = type;
}

public HashMap<String, String> getHeaders() {
	return headers;
}

public void setHeaders(HashMap<String, String> headers) {
	this.headers = headers;
}

public Method getMethod() {
	return method;
}

public void setMethod(Method method) {
	this.method = method;
}

public static class JerseyRequestBuilder {

    private String url;
    private String body;
    private String type;
    private HashMap<String, String> headers = new HashMap<String, String>();
    private Method method;

    public JerseyRequestBuilder(String url, String type, Method method) {
      this.url = url;
      this.type = type;
      this.method = method;
    }

    public JerseyRequestBuilder body(String body) {
      this.body = body;
      return this;
    }

    public JerseyRequestBuilder headers(HashMap<String, String> headers) {
      this.headers = headers;
      return this;
    }

    public JerseyRequest build() {
      return new JerseyRequest(this);
    }
  }
}
