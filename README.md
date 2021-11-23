# Description of Project
This project is framework that covers ReactShopping application automation using Selenium with Java, Maven and Testng.
Project is created using Maven and Page object model design pattern is used to automate various scenarios of application.
All test cases reside in ReactShopping\src\test\java\testCases package and locators with required methods in ReactShopping\src\test\java\pages package.
There is ReactShopping\Config\Configuration.properties file that has details of application and URL and driver's exe location.
Whenever any test case fails then screenshot will be taken and stored in ReactShopping\Screenshots folder.
Once the execution is completed, user can check the result by using ReactShopping\test-output\emailable-report.html page in any browser.

# Prerequisites to run this project
This project is build using eclipse IDE and can easily be configured in any machine having eclipse.
Eclipse should have testng installed and Maven configured.

# How to run this project
Download the project and import as Maven project in eclipse ide. Open ReactShopping\Config\Configuration.properties file and update chromedriver and gekodriver path for Chrome
browser and Firefox browser respectively. 
Open Testng.xml and update the value of browser parameter. This will accept chrome or Firefox values. 
Right click on Testng.xml and run as Test NG suite.
Once the execution is completed open ReactShopping\test-output\emailable-report.html page in any browser to see report.

