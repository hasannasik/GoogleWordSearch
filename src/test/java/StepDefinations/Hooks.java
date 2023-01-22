package StepDefinations;

import Utilities.ExcelUtility;
import Utilities.GWD;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Hooks {
    @Before
    public void before()
    {
        System.out.println("Senaryo basladi.");
    }
    @After
    public void after(Scenario scenario) throws IOException {
        System.out.println("Senaryo bitti.");

        LocalDateTime localDateTime=LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter=DateTimeFormatter.ofPattern("dd_MM_YYHHmmss");
        // senaryolarin sonuclarini bir excel formatinda yazdirmak istiyorum

        ExcelUtility.writeToExcel("src/test/java/ApachePOI/resource/ScenarioStatus.xlsx",
                scenario,GWD.getThreadBrowserName(), localDateTime.format(dateTimeFormatter));


        if (scenario.isFailed())
        {
            final byte[] screenshot = ((TakesScreenshot) GWD.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenshot, "image/png", scenario.getName());

//            TakesScreenshot ts= (TakesScreenshot) GWD.getDriver();
//            File hafizadakiHali=ts.getScreenshotAs(OutputType.FILE);
//
//            LocalDateTime time=LocalDateTime.now();
//            DateTimeFormatter tf= DateTimeFormatter.ofPattern("dd_MM_YYHHmmss");
//            try {
//                FileUtils.copyFile(hafizadakiHali, new File("ekranGoruntuleri\\screenshot_"+time.format(tf)+".png"));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }
        GWD.quitDriver();
    }
}
