.DEFAULT_GOAL := build-run

setup:
	./app/gradlew wrapper --gradle-version 8.4

clean:
	./app/gradlew clean

build:
	./app/gradlew clean build

install:
	./app/gradlew clean install

run-dist:
	./app/build/install/java-package/bin/java-package

run:
	./app/gradlew run

test:
	./app/gradlew test

report:
	./app/gradlew jacocoTestReport

lint:
	make -C app lint

check-deps:
	./app/gradlew dependencyUpdates -Drevision=release

build-run: build run

.PHONY: build