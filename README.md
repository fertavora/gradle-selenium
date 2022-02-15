# Selenium WebDriver: Java - Gradle - TestNG - Allure

# Requirements
## Pre conditions
Before cloning this repo and run tests, make sure you already have the following requirements installed:
* Java 11
* Google Chrome

Note: You do not need to install Gradle. This framework uses the Gradle 6.9.2 in Gradle Wrapper.

## Environment Variables
This framework requires the `ENV_BASE_URL` environment variable to be set.<br>It refers to the URL of the app under test and it helps to be able to run tests on different environments URLs with no code changes.

e.g. `ENV_BASE_URL=https://www.saucedemo.com`

## Chrome Driver
This framework implements [WebDriverManager](https://bonigarcia.dev/webdrivermanager/) so you do not need to worry about ChromeDriver versions and artifacts.<br>For the moment, these tests run only on Google Chrome.

# Application under test
This framework run tests on the [SauceDemo](https://www.saucedemo.com) web application.