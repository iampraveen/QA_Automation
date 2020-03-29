 package runners;

import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.CucumberOptions;

@CucumberOptions(
 features = "src/test/resources/functionalTests",
		 glue= {"stepDefinitions"},
		 tags= {"@regression"},
				 plugin = { "pretty", "json:target/cucumber-reports/Cucumber.json",
						 "junit:target/cucumber-reports/Cucumber.xml",
						 "html:target/cucumber-reports"},
		 monochrome = true
 )

public class TestRunner extends AbstractTestNGCucumberTests {

}
