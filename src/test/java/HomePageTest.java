import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HomePageTest {
    private WebDriver driver;
    HomePage homePage;

    @BeforeTest
    public void setUp() {
        System.setProperty("webdriver.chromedriver", "C:\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        homePage = new HomePage(driver);
    }


    @Test(description = "Search for valid result")
    public void testSearchForValidResult() {
        String searchValue = "фантастика";

        System.out.println("Step 1. Open 'Labirint' Home Page");
        driver.get("https://www.labirint.ru/");

        System.out.println("Step 2. Fill search field with valid value and click the 'Search' button");
        homePage.pageLoading();
        homePage.fillSearchInputField(searchValue);
        homePage.clickSearchButton();
        System.out.println("Attempt to confirm search result is noy empty and search value is visible in search result");
        Assert.assertEquals(homePage.getSearchResultMarkerFieldAttribute(), searchValue,
                "Can't see a test value in a marker field");
        Assert.assertTrue(homePage.getResultsCount() > 0, "Search results amount equals zero");
    }

    @Test(description = "Search for invalid result")
    public void testSearchForInvalidResult() {
        String searchValue = "голкондрина для поклябывания";
        String errorMessage = "Мы ничего не нашли по вашему запросу! Что делать?";

        System.out.println("Step 1. Open 'Labirint' Home Page");
        driver.get("https://www.labirint.ru/");

        System.out.println("Step 2. Fill search field with invalid value and click the 'Search' button");
        homePage.pageLoading();
        homePage.fillSearchInputField(searchValue);
        homePage.clickSearchButton();
        System.out.println("Attempt to confirm search result is not empty and search value is visible in search result");
        Assert.assertEquals(homePage.getSearchResultErrorMessage(), errorMessage,
                "Error message is not visible");
        Assert.assertEquals(homePage.getResultsCount(), 0, "Search result is empty");
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}
