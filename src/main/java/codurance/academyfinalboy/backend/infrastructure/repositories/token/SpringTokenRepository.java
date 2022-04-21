package codurance.academyfinalboy.backend.infrastructure.repositories.token;

import codurance.academyfinalboy.backend.model.token.Token;
import codurance.academyfinalboy.backend.model.token.TokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class SpringTokenRepository implements TokenRepository {
    @Override
    public long save(Token token) {
        throw new UnsupportedOperationException();
    }
}
