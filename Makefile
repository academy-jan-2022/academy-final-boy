
up:
	@docker compose up -d

down:
	docker compose down

build-backend:
	docker build -t academy/teaminator-back .

start-db:
	docker compose up -d db

test:
	./gradlew test

run-test: up test down