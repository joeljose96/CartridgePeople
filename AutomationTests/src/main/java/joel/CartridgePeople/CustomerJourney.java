package joel.CartridgePeople;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;
import java.util.Random;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CustomerJourney {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "AutomationTests/chromedriver");

        // Instantiate a ChromeDriver class.
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,30);

        // Launch Website
        driver.navigate().to("https://www.cartridgepeople.com");

        //accept cookies
        wait.until(ExpectedConditions.elementToBeClickable(By.id("CybotCookiebotDialogBodyButtonAccept")));
        WebElement acceptCookie = driver.findElement(By.id("CybotCookiebotDialogBodyButtonAccept"));
        acceptCookie.click();

        //navigate to printers page
        Actions action = new Actions(driver);
        By inkjetPrinters = By.linkText("Inkjet Printers");
        action.moveToElement(driver.findElement(By.xpath("/html/body/header/div[2]/nav/div[1]/ul/li[5]/a"))).perform();
        wait.until(ExpectedConditions.elementToBeClickable(inkjetPrinters));
        driver.findElement(inkjetPrinters).click();
        //Canon Pixma MG2550S MFC Printer was out of stock so was not shown on the printers page, choosing to go on other printer page

        By printer = By.linkText("Canon PIXMA GM4050 A4 Mono Multifunction Inkjet Printer (Wireless)");
        wait.until(ExpectedConditions.elementToBeClickable(printer));
        driver.findElement(printer).click();

        By addToBasket = By.xpath("/html/body/div[2]/div/div[2]/div/div/form/button");
        wait.until(ExpectedConditions.elementToBeClickable(addToBasket));
        driver.findElement(addToBasket).submit();

        //checkout
        By checkout = By.xpath("//*[@id=\"add-to-basket-prompt\"]/div/div/div[3]/div[2]/a");
        wait.until(ExpectedConditions.elementToBeClickable(checkout));
        driver.findElement(checkout).click();

        //checkout securely
        By checkoutSecure = By.xpath("//*[@id=\"basket-checkout-links\"]/div/a[1]");
        //Have to use a java script executor as usual find by method was not working
        wait.until(ExpectedConditions.elementToBeClickable(checkoutSecure));
        WebElement checkoutSecurely=driver.findElement(checkoutSecure);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click()",checkoutSecurely);

        //Register a new account
        By radioRegister = By.id("registerRadio");
        By registerLink = By.id("registerLink");
        wait.until(ExpectedConditions.elementToBeClickable(radioRegister));
        driver.findElement(radioRegister).click();

        wait.until(ExpectedConditions.elementToBeClickable(registerLink));
        driver.findElement(registerLink).click();

        //Input registration details
        wait.until(ExpectedConditions.elementToBeClickable(By.id("FullName")));
        driver.findElement(By.id("FullName")).sendKeys("Joel Jose");
        //Random number added to the email as every time automation is run a new account is created and new email address is required
        Random random = new Random();
        driver.findElement(By.id("EmailAddress")).sendKeys("joel" + random.nextInt(1000) + "@mail.com");
        driver.findElement(By.id("password")).sendKeys("somePassword1!");
        driver.findElement(By.xpath("/html/body/div/div[2]/div/div/div/form/button")).click();

        //Input checkout details
        Select title = new Select(driver.findElement(By.id("Address_TitleId")));
        title.selectByVisibleText("Mr");
        driver.findElement(By.id("Address_CustomerNameFull")).sendKeys("Joel Jose");
        driver.findElement(By.id("Address_Postcode")).sendKeys("CW1 6NG");
        driver.findElement(By.id("Address_StreetAddress")).sendKeys("1 Street");
        driver.findElement(By.id("Address_City")).sendKeys("Crewe");
        driver.findElement(By.id("Address_Phone")).sendKeys("0786687756487658");
        driver.findElement(By.id("Address_Mobile")).sendKeys("0786687756487658");
        driver.findElement(By.id("Next-Button")).click();

        driver.quit();
    }
}
