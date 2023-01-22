package StepDefinations;

import Pages.Content;
import Utilities.ExcelUtility;
import Utilities.GWD;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MyStepdefs  {
    Content content = new Content();
    XSSFWorkbook workbook = new XSSFWorkbook();
    XSSFSheet sheet = workbook.createSheet("Search Results");


    @Given("Search for IBTECH on Google")
    public void searchForIBTECHOnGoogle() {
        GWD.getDriver().get("https://www.google.com/");
        GWD.getDriver().manage().window().maximize();
        content.findAndSend("searchBox","IBTECH");
        content.findAndClick("ibtech");

    }


    @And("First page results are listed and The results are exported to excel")
    public void firstPageResultsAreListedAndTheResultsAreExportedToExcel() throws IOException {
        List<WebElement> linkList=content.links;
        List<WebElement> textList=content.texts;
        for (WebElement e:linkList)
        {

            System.out.println("e.getAttribute(\"href\") = " + e.getAttribute("href"));
        }
        for (WebElement e:textList)
        {

            System.out.println("e.getText() = " + e.getText());
        }


        for (int i = 0; i < linkList.size(); i++) {
            WebElement result = linkList.get(i);
            String text = result.getText();
            String link = result.getAttribute("href");

            sheet.createRow(i).createCell(0).setCellValue(text);
            sheet.getRow(i).createCell(1).setCellValue(link);
        }

        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(new File("src/test/CignaTest.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Search Results written successfully on disk.");

    }


    @And("Second page results are found and exported to excel")
    public void secondPageResultsAreFoundAndExportedToExcel() throws IOException {
        List<WebElement> linkList=content.links;
        List<WebElement> textList=content.texts;
        content.findAndClick("page2");

        for (WebElement e:linkList)
        {

            System.out.println("e.getAttribute(\"href\") = " + e.getAttribute("href"));
        }
        for (WebElement e:textList)
        {

            System.out.println("e.getText() = " + e.getText());
        }
//        XSSFSheet sheet = workbook.createSheet("Search Results Second Page");

        for (int i = 0; i < linkList.size(); i++) {
            WebElement result = linkList.get(i);
            String text = result.getText();
            String link = result.getAttribute("href");
            int sonSatir=sheet.getPhysicalNumberOfRows();

            sheet.createRow(sonSatir).createCell(0).setCellValue(text);
            sheet.getRow(sonSatir).createCell(1).setCellValue(link);
        }

        //Write the workbook in file system
        FileOutputStream out = new FileOutputStream(new File("src/test/CignaTest.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Search Results written successfully on disk.");
    }

    @And("The results on the last page are listed and exported to excel")
    public void theResultsOnTheLastPageAreListedAndExportedToExcel() throws IOException {
        List<WebElement> linkList=content.links;
        List<WebElement> textList=content.texts;
        content.findAndClick("page3");


        for (int i = 0; i < linkList.size(); i++) { //
            WebElement result = linkList.get(i);
            String text = result.getText();
            String link = result.getAttribute("href");
            int sonSatir=sheet.getPhysicalNumberOfRows();

            sheet.createRow(sonSatir).createCell(0).setCellValue(text);
            sheet.getRow(sonSatir).createCell(1).setCellValue(link);
        }


        FileOutputStream out = new FileOutputStream(new File("src/test/CignaTest.xlsx"));
        workbook.write(out);
        out.close();
        System.out.println("Search Results written successfully on disk.");
        GWD.quitDriver();
    }
    }

