package codurance.academyfinalboy.backend.user;

import java.util.UUID;

record LoginRequest(UUID externalId, String fullName) {}
