compile:
	@echo "Compile the application"
	@mvn compile

build-without-tests:
	@echo "Building the application without running tests"
	@mvn clean install -U -DskipTests

bdd-test:
	@echo "Running BDD tests"
	@mvn test -P bdd-tests

unit-test:
	@echo "Running unit tests"
	@mvn test -P unit-tests

unit-test-coverage:
	@echo "Running unit tests with coverage"
	@mvn clean test -P unit-tests

test: unit-test

package:
	@echo "Packaging the application"
	@mvn package

start-app:
	@echo "Starting the application"
	@mvn spring-boot:start

stop-app:
	@echo "Stopping the application"
	@mvn spring-boot:stop