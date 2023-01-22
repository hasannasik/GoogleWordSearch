package Pages;

import Utilities.GWD;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class Content extends Parent {
    WebElement myElement;

    public Content() {
        PageFactory.initElements(GWD.getDriver(), this);
    }

    @FindBy(css = "[title='Ara']")
    private WebElement searchBox;

    @FindBy(xpath = "//span[text()='IBTECH Uluslararası Bilişim ve İletişim Teknolojileri']")
    private WebElement ibtech;

    @FindBy(css = "[class='yuRUbf']>a")
    public List<WebElement> links;

    @FindBy(css = "[class='yuRUbf']>a>h3[class='LC20lb MBeuO DKV0Md']")
    public List<WebElement> texts;

    @FindBy(css = "[aria-label='Page 2']")
    private WebElement page2;

    @FindBy(css = "[aria-label='Page 3']")
    private WebElement page3;


    public void findAndSend(String strElement, String value) {
        switch (strElement) {

            case "searchBox":
                myElement = searchBox;
                break;
//            case "":myElement = ;break;
//            case "":myElement = ;break;
//            case "":myElement = ;break;

        }
        sendKeysFunction(myElement, value);

    }

    public void findAndClick(String strElement) {
        switch (strElement) {

            case "ibtech":
                myElement = ibtech;
                break;
            case "page2":
                myElement = page2;
                break;
            case "page3":
                myElement = page3;
                break;
//            case "":myElement = ;break;

        }
        clickFunction(myElement);
    }
}

