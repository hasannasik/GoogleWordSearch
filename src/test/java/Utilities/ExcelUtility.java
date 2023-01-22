package Utilities;

import io.cucumber.java.Scenario;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class ExcelUtility {
    // kendisine verilen path deki excelin, istenilen, sheetindeki
    // istenilen kolona kadar sütunları okuyup Arraylist formatında geri döndüren fonksiyonu yazınız.
    // getListData("src/test/java/ApachePOI/resources/ApacheExcel2.xlsx","testCitizen", 2); 0-2

    public static ArrayList<ArrayList<String>> getListData(String path, String sheetName, int columnCnt) {
        ArrayList<ArrayList<String>> tablo = new ArrayList<>();
        Sheet sayfa = null;
        try {
            FileInputStream inputStream = new FileInputStream(path);
            Workbook workbook = WorkbookFactory.create(inputStream);
            sayfa = workbook.getSheet(sheetName);
        } catch (Exception e) {
            System.out.println("e.getMessage() = " + e.getMessage());
        }


        for (int i = 0; i < sayfa.getPhysicalNumberOfRows(); i++) { // butun satirlari aliyoruz
            ArrayList<String> satirData = new ArrayList<>();
            for (int j = 0; j < columnCnt; j++) {
                satirData.add(sayfa.getRow(i).getCell(j).toString());
            }
            tablo.add(satirData);
        }
        return tablo;


    }

    public static void writeToExcel(String path, Scenario scenario, String browserName, String time) {
        File file = new File(path);

        if (!file.exists()) {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Cigna Test");

            Row newRow = sheet.createRow(0); // 0 cunku ilk satir
            Cell newCell = newRow.createCell(0);
            newCell.setCellValue(scenario.getName());

            newCell = newRow.createCell(1);
            newCell.setCellValue(scenario.getStatus().toString());

            try {
                FileOutputStream outputStream = new FileOutputStream(path);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (Exception e) {
                System.out.println("e.getMessage() = " + e.getMessage());
            }


        } else {
            Sheet sheet = null;
            Workbook workbook=null;
            FileInputStream inputStream=null;
            try {
                inputStream = new FileInputStream(path);
                workbook = WorkbookFactory.create(inputStream);
                sheet = workbook.getSheetAt(0);

            } catch (Exception e) {
                System.out.println("e.getMessage() = " + e.getMessage());
            }
            int enSonSatir = sheet.getPhysicalNumberOfRows();/*en son satir sayisi*/
            Row newRow = sheet.createRow(enSonSatir);


            Cell newCell = newRow.createCell(0);
            newCell.setCellValue(scenario.getName());

            newCell = newRow.createCell(1);
            newCell.setCellValue(scenario.getStatus().toString());

            newCell = newRow.createCell(2);
            newCell.setCellValue(browserName);

            newCell = newRow.createCell(3);
            newCell.setCellValue(time);
            try {
                inputStream.close();
                FileOutputStream outputStream = new FileOutputStream(path);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            } catch (Exception e) {
                System.out.println("e.getMessage() = " + e.getMessage());
            }
        }


    }


    public static void main(String[] args) {
        ArrayList<ArrayList<String>> tablo =
                getListData("src/test/java/ApachePOI/resource/ApacheExcel2.xlsx", "testCitizen", 3);

        System.out.println("tablo = " + tablo);
    }
}
