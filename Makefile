run-test:
	@docker compose up -d && \
	./gradlew test && \
	docker compose down