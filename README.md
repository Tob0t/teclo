# teclo

Automated test cases for Bugzilla

# Getting started

Before being able to run the test cases, the following steps need to be executed
  - Start Bugzilla virtual machine
  - Make sure that Bugzilla virtual machine is running and the language is set to English
  - Open the file `config.properties` in the `src/` folder and replace the variable ``BASE_URL`` with the **IP address** of your Bugzilla virtual machine
  - Run all test cases by running the test suite "AllTests.java"
