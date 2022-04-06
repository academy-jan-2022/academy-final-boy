package codurance.academyfinalboy.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TokenVerificationController.class)
public class TokenVerificationShould {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unauthorized_calls_without_a_valid_token_return_a_401_error() throws Exception {
        String token = "";

        mockMvc.perform(post("/tokenvalidator")
                        .content(token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void authorized_calls_with_a_valid_token_return_a_200() throws Exception {
        String token = "iAmValid";

        mockMvc.perform(post("/tokenvalidator")
                        .content(token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
