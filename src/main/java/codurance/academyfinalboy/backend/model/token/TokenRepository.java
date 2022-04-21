package codurance.academyfinalboy.backend.model.token;

public interface TokenRepository {
  Token save(Token token);

    void clear();
}
