run-dev:
	@docker compose --env-file .env.dev up -d

down:
	docker compose --env-file .env.dev down

build-backend: format
	docker build -t academy/teaminator-back .

start-db:
	docker compose --env-file .env.test up -d db

format:
	./gradlew :spotlessApply

test:
	./gradlew test

run-test: start-db test down