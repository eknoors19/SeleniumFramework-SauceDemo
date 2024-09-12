Introduction -
--------------

This automation framework is designed to test saucedemo website with help of automated scripts. 
These scripts takes necessary inputs like website url from user and browser name in config.properties file and user credential from excel file. 
This framework generate extent report in html format with screenshots attached in report only for failed test cases.



Requirements -
--------------

In order to run this automation suite, you will need following requirements configured in your system:
1. Java 1.8 (including both JDK and JRE with version 1.8)
2. Eclipse

And following requirements as dependencies configured in pom.xml file:
1. Maven
2. TestNG
3. Selenium Driver



Installation -
--------------

1. Java 1.8-
	Install jdk and and jre from links given below.
	https://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

	Once you have install both, update your environment variables as given below-
	For user variable- create new variable with name as 'JAVA_HOME' and value as path of installed jdk. (Include path till jdk folder).

2. Eclipse-
	Install eclipse by downloading from link given below.
	https://www.eclipse.org/downloads/packages/release/photon/r/eclipse-ide-java-ee-developers

3. Maven-
	Add required dependency in pom.xml file to add maven drivers.

4. TestNG-
	Add required dependency in pom.xml file to add TestNG drivers.

5. Selenium Driver-
	Add required dependency in pom.xml file to add TestNG drivers.


Configuration -
---------------

1. Open eclipse and open downloaded project in eclipse by clicking 'Open projects from file system' option present in File menu.
2. Install testng plugin from Eclipse Marketplace given in Help menu.
3. Change test data in config.properties file and testdata.properties file.
4. Add required dependencies in pom.xml file for maven and testng plugins. 



Points to remember -
--------------------

1. Before running this automation suite, login credentials need to be selected. Valid credentials are addedd in excel test sheet "TestData.xlsx".
2. You can run "SmokeTest.xml" file for paralled execution of couple of testcases.
3. For Maven execution, you can run pom.xml file by Run As "Maven Test". 
4. For testng execution, you can run "testng.xml" file by Run As "TestNG SUite".