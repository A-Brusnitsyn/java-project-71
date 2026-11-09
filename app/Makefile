.PHONY: build clean test lint install run-dist

build:
	./gradlew build

clean:
	./gradlew clean

test:
	./gradlew test

lint:
	./gradlew spotlessCheck

install:
	./gradlew installDist

run-dist:
	./build/install/app/bin/app