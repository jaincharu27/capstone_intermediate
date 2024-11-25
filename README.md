**Java Case Study: Automated Testing to Verify Stock Information Display**
The objective of this an automated test using Java and Selenium to verify the profit or loss of a specific stock on the NSE India website.
Description:
Ensure that the stock information, including the current market value, is displayed correctly on the NSE India website after searching for the stock "RCOM."

**Setup:**
○ Configure a Maven Java project with Selenium for web automation.
○ Clone project repo
○ Run testng.xml
○ Run allure serve allure-result command at terminal and see report
○ Logs will generate at myLog file

**Test case for automation script**
● Navigation to NSE India Website: (https://www.nseindia.com/).
● Search for Stock e.g. "RCOM"- Dynamic and for All:
● Retrieve and print 52 weeks high and low prices of the stock.

○ Test script runs in parallel across multiple browsers (e.g., Chrome, Firefox, Edge).
○ Screenshots of the webpage will generate in case of test case failure.

**Steps to setup Selenium TestNG Allure Report**
Step1: Download "allure-commandline-2.17.3.zip" from below url
Step2: Set system environment variables for bin folder
Step3: Verify installation using cmd prompt, enter "allure --version" in cmd.
version must be displayed
Step4: Add below maven dependency at pom.xml
  <dependency>
    <groupId>io.qameta.allure</groupId>
    <artifactId>allure-testng</artifactId>
    <version>2.17.2</version>
  </dependency>
Step6: Execute testng suite file
Step7: Refresh project & enter "allure serve "allure-reportpathfromproject"" in cmd or Just enter "allure serve" for rest assured tests.
Step8: Report will be opened in browser after entering above cmd.
