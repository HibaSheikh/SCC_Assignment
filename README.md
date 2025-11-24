# My Applications UI Automation

Automates validation of the My Applications grid for multiple users.

## Tech Stack

- Java 17
- Selenium WebDriver
- TestNG
- Maven

## Project Structure

- `pages/` → Page Objects
- `models/` → ApplicationRecord POJO
- `utils/` → CsvDataReader
- `test/java/` → Test classes
- `resources/` → TestData.csv

## How to Run

```bash
mvn clean test -DsuiteXmlFile=testng.xml
```
