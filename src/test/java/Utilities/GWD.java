package Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

// GENERAL WEB DRIVER
public class GWD {
   // private static WebDriver driver;
    private static ThreadLocal<WebDriver> threadDriver=new ThreadLocal<>(); // webDriver1 , webDriver2 ...
    private static ThreadLocal<String > threadBrowserName=new ThreadLocal<>(); // chrome , firefox ...

    // threadDriver.get() > bulundugum thread deki driveri verecek
    // threadDriver.set(driver) -> bulundugum thread e driver set edecek


    public static WebDriver getDriver() {
        // extend report türkçe bilg çalışmaması sebebiyle kondu
        Locale.setDefault(new Locale("EN"));
        System.setProperty("user.language", "EN");
        Logger.getLogger("").setLevel(Level.SEVERE);
        System.setProperty(org.slf4j.simple.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Error");

        if (threadBrowserName.get() == null)  // diğer testlerimizi direk çalıştırırken, XML den parametre gelmeyeceği için
            threadBrowserName.set("chrome");  // default olarak chrome atandı

        if (threadDriver.get() == null) {

            switch (threadBrowserName.get()) {

                // buralara diger browserleri ekleyecegiz

                case "firefox":
                    //System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
                    WebDriverManager.firefoxdriver().setup();
                    threadDriver.set(new FirefoxDriver());
                    break;


                case "edge":
                    //System.setProperty(EdgeDriverService.EDGE_DRIVER_SILENT_OUTPUT_PROPERTY,"true");
                    WebDriverManager.edgedriver().setup();
                    threadDriver.set(new EdgeDriver());
                    break;

                case "chrome":
                    System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");
                    WebDriverManager.chromedriver().setup();
                    threadDriver.set(new ChromeDriver());

                    break;

            }
        }
        return threadDriver.get();
    }

    public static void quitDriver() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        if (threadDriver.get() != null) {
            threadDriver.get().quit();
            WebDriver driver = threadDriver.get();
            driver=null;
            threadDriver.set(driver);
        }
    }


    public static void setThreadBrowserName(String browserName) {
        GWD.threadBrowserName.set(browserName);
    }

    public static String getThreadBrowserName() {
      return GWD.threadBrowserName.get();
    }


}
