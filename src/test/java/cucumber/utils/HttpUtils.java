package cucumber.utils;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.springframework.boot.web.server.LocalServerPort;

public class HttpUtils {
  @LocalServerPort private int port;

  @Before
  public void setupRestAssured() {
    RestAssured.port = port;
  }
}
