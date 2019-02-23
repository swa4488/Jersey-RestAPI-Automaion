package test;

import org.testng.annotations.Test;
import clients.ApiAction;
import clients.models.BasicAuthResponse;

public class ApiTest {
  @Test()
  public void apiTest() {
      ApiAction apiAction = new ApiAction();
      BasicAuthResponse response = apiAction.getBasicPostman("/basic-auth");
      System.out.println(response);
  }

}
