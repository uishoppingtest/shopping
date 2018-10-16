# shopping

To run the tests use ./gradlew clean test allureReport command

Allure reports will be stored in api-tests/build/reports/allure-report

Solution consists of several parts:
- Page classes
- Browser factory
- Asserts
- Helpers (wait conditions)
- Logging (appropriate aspect is used for this purposes)
- Reporting (Allure)
- Test flow script

Verifications that some page is open is done in the constructors of pages so it's not verified explicitly in the test method.
