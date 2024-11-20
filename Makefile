build:
	@echo "Building the application"
	@mvn compile

unit-test:
	@echo "Running unit tests"
	@mvn test

test-coverage:
	@echo "Running unit tests with coverage"
	@mvn clean test jacoco:report

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
