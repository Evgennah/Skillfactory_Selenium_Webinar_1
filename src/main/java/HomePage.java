import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {
    private WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    /* **************************************
     *************** Locators ***************
     ***************************************/

    private static final String HOME_PAGE_WRAPPER = "//div[@class='body-main-content-wrapper']";
    private static final String LOGO = HOME_PAGE_WRAPPER + "//span[@class='b-header-b-logo-e-logo']";
    private static final String SEARCH_INPUT_FIELD = HOME_PAGE_WRAPPER + "//input[@id='search-field']";
    private static final String SEARCH_BUTTON = HOME_PAGE_WRAPPER + "//button[@type='submit']";
    private static final String SEARCH_RESULT_MARKER_FIELD = HOME_PAGE_WRAPPER + "//input[@class='text navisort-find-text ']";
    private static final String SEARCH_RESULT_ITEM = HOME_PAGE_WRAPPER + "//div[@class='product-cover__cover-wrapper']";
    private static final String SEARCH_RESULT_ERROR_MESSAGE = HOME_PAGE_WRAPPER + "//div[@class='search-error']//h1";

    /* **************************************
     *************** Methods ****************
     ***************************************/

    public void pageLoading() {
        try {
            if (!((driver.findElement(By.xpath(LOGO))).isDisplayed()))
                throw new Exception();
        } catch (Exception ex) {
            throw new AssertionError("The 'Main Page' was not loaded");
        }
    }

    public void fillSearchInputField(String searchValue) {
        driver.findElement(By.xpath(SEARCH_INPUT_FIELD)).sendKeys(searchValue);
    }

    public void clickSearchButton() {
        driver.findElement(By.xpath(SEARCH_BUTTON)).click();
    }

    public String getSearchResultMarkerFieldAttribute() {
        return driver.findElement(By.xpath(SEARCH_RESULT_MARKER_FIELD)).getAttribute("value");
    }

    public int getResultsCount() {
        List<WebElement> results = driver.findElements(By.xpath(SEARCH_RESULT_ITEM));
        return results.size();
    }

    public String getSearchResultErrorMessage() {
        return driver.findElement(By.xpath(SEARCH_RESULT_ERROR_MESSAGE)).getText();
    }
}
