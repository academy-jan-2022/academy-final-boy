package codurance.academyfinalboy.backend.web.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import codurance.academyfinalboy.backend.BaseSpringTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
class CorsShould extends BaseSpringTest {

  @Autowired MockMvc mvc;

  @Test
  void allow_heartbeat_api() throws Exception {
    mvc.perform(
            get("/actuator/health")
                .header("Access-Control-Request-Method", "GET")
                .header("Origin", "http://localhost:3000"))
        .andExpect(status().isOk())
        .andExpect(header().string("Access-Control-Allow-Origin", "*"));
  }
}
