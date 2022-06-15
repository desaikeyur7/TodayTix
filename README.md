# Java Junit Automation framework

## Project Structure
### src/main/java/org.TodayTix
###   1. PageObjects - Create separate .java files for each page
###   2. BasePage - Instantiate WebDriver
###   3. BrowserSelector - Select the required browser to run tests
###   4. LoadProp - Passing data used in framework 
###   5. Utils - Reusable methods

### src/test/java/org.TodayTix
###   1. Tests - Write automation tests
###   2. BaseTest - Define Junit Annotations 

### src/test/Resources/ BrowserDrivers
###  1. chromedriver.exe - To run tests in chrome browser

### src/test/Resources/ TestData
###  1. TestDataConfig.properties - Pass data used in framework

## pom.xml - Define dependencies to use in framework

##Test Execution##
1. Go to src/test/java/org.TodayTix/Tests/Test1.java 
   a. Click on the play icon displayed on the left side of the pane
   b. Right click anywhere in the right pane and click run

##Requires Java version 8##
<details><summary>Click me to find a link for download</summary>
<p>
https://www.java.com/download/ie_manual.jsp
</p>
</details>








