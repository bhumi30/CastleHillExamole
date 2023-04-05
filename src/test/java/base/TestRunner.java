package base;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/java/features", 
glue = {"src/test/java/stepdefinitions"},
monochrome=true,
plugin = {"pretty","json:target/reports/cucumber.json"}
)
public class TestRunner {

}
