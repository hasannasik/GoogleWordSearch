package Runners;


import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions
        (
                tags = "@Regression",
                features = {"src/test/java/FeatureFiles/WriteToExcel.feature"},
                glue = {"StepDefinations"},
                plugin = {"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"}
        )
public class ExtendReport extends AbstractTestNGCucumberTests {

}
