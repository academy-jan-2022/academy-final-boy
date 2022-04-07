package codurance.academyfinalboy.backend;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TokenVerificationController.class)
public class TokenVerificationShould {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    GoogleTokenValidator googleTokenValidator;

    @Test
    void unauthorized_calls_without_a_valid_token_return_a_401_error() throws Exception {
        String token = "";
        when(googleTokenValidator.authenticateToken(token)).thenReturn(false);

        mockMvc.perform(get("/tokenvalidator")
                        .header("Authorization", token))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void authorized_calls_with_a_valid_token_return_a_200() throws Exception {
        String token = "iAmValid";

        when(googleTokenValidator.authenticateToken(token)).thenReturn(true);

        mockMvc.perform(get("/tokenvalidator")
                        .header("Authorization", token))
                .andExpect(status().isOk());
    }
}
