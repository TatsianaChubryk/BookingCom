package steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.setWebDriver;

public class BookingTaxiSteps {
    String pickUpLocation;
    String destination;

    @BeforeMethod
    public void initTest() {
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> prefs = new HashMap<>();
        options.addArguments("--disable-popup-blocking");
        prefs.put("profile.default_content_setting_values.notifications", 2);
        options.setExperimentalOption("prefs", prefs);
        WebDriver driver = new ChromeDriver(options);
        setWebDriver(driver);

        Configuration.browser = "chrome";
        Configuration.timeout = 15000;
        Configuration.headless = false;
        Configuration.browserSize = "1024x768";
    }

    @Given("User is looking for taxi")
    public void userIsLookingForTaxi() {
        open("https://www.booking.com/taxi/");
    }

    @And("User selects a Enter pick-up location {string}")
    public void userSelectsAEnterPickUpLocation(String pickUpLocation) {
        this.pickUpLocation = pickUpLocation;
        $(By.name("pickup")).setValue(pickUpLocation);
    }

    @And("User selects a Enter destination {string}")
    public void userSelectsAEnterDestinationa(String destination) {
        this.destination = destination;
        $(By.name("dropoff")).setValue(destination);
    }

    @And("User selects a date and passengers")
    public void userSelectsADateAndPassengers() {
        $(By.cssSelector("[data-testid='taxi-date-time-picker-form-element__button-oneway']")).click();
        $(By.cssSelector("[data-date='2025-04-04']")).click();
        $(By.xpath("//*[@id=':rc:']/option[5]")).click();
    }

    @When("User does search a taxi")
    public void userDoesSearchATaxi() {
        $(By.cssSelector("[data-testid='submit-button']")).click();
    }

    @Then("Page with {string} show")
    public void pageWithTheLatestPricesFromOurTrustedPartnersShow(String expectedText) {
        String actualText = $x("data-test='taxi-car-card-wrapper__title").getText();
        Assert.assertEquals(actualText, expectedText);
    }

    /*@AfterMethod
    public void endTest() {
        getWebDriver().quit();
    }*/
}
