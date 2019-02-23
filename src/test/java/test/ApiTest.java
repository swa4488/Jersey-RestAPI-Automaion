package test;

import org.testng.annotations.Test;
import clients.ApiAction;
import clients.models.response.BasicAuthResponse;

public class ApiTest {
  @Test(enabled= false)
  public void apiTest() {
      ApiAction apiAction = new ApiAction();
      BasicAuthResponse response = apiAction.getBasicPostman("/basic-auth");
      System.out.println(response);
  }
}
