up:
	@docker compose up -d

down:
	docker compose down

test:
	./gradlew test

run-test: up test down