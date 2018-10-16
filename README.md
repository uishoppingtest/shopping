# shopping

To run the tests use ./gradlew clean test allureReport command

Allure reports will be stored in api-tests/build/reports/allure-report

Verifications that some page is open is done in the constructors of pages so it's not verified explicitly in the test method.
