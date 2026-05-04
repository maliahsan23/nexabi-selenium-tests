package tests;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;

public class NexaBITests extends BaseTest {

    private static final String BASE_URL = "https://nexabi.vercel.app";
    private static final String EMAIL = "maa62390@gmail.com";
    private static final String PASSWORD = "Ali1234";

    @Test
    public void testHomePageLoad() {
        driver.get(BASE_URL);
        Assert.assertTrue(driver.getTitle().length() > 0);
    }

    @Test
    public void testLoginPageLoad() {
        driver.get(BASE_URL + "/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("login"));
        Assert.assertTrue(driver.getCurrentUrl().contains("login"));
    }

    @Test
    public void testValidLogin() {
        driver.get(BASE_URL + "/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")));
        email.sendKeys(EMAIL);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testInvalidLogin() {
        driver.get(BASE_URL + "/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")));
        email.sendKeys("wrong@email.com");
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys("wrongpass");
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        Assert.assertFalse(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testSignupPageLoad() {
        driver.get(BASE_URL + "/signup");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("signup"));
        Assert.assertTrue(driver.getCurrentUrl().contains("signup"));
    }

    @Test
    public void testStartFreeButton() {
        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Start Free')]")));
        Assert.assertTrue(btn.isDisplayed());
    }

    @Test
    public void testFeaturesNavigation() {
        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement features = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Features')]")));
        features.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("nexabi"));
    }

    @Test
    public void testPricingNavigation() {
        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement pricing = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Pricing')]")));
        pricing.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("nexabi"));
    }

    @Test
    public void testPageTitle() {
        driver.get(BASE_URL);
        String title = driver.getTitle();
        Assert.assertNotNull(title);
        Assert.assertTrue(title.length() > 0);
    }

    @Test
    public void testLoginButtonExists() {
        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement loginBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Log In')]")));
        Assert.assertTrue(loginBtn.isDisplayed());
    }

    @Test
    public void testDashboardAfterLogin() {
        driver.get(BASE_URL + "/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement email = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("input[type='email']")));
        email.sendKeys(EMAIL);
        driver.findElement(By.cssSelector("input[type='password']")).sendKeys(PASSWORD);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
        wait.until(ExpectedConditions.urlContains("dashboard"));
        Assert.assertTrue(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testEmptyLoginFields() {
        driver.get(BASE_URL + "/login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[type='submit']")));
        btn.click();
        Assert.assertFalse(driver.getCurrentUrl().contains("dashboard"));
    }

    @Test
    public void testLogoVisible() {
        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement logo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'Nexa')]")));
        Assert.assertTrue(logo.isDisplayed());
    }

    @Test
    public void testWatchDemoButton() {
        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement demo = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[contains(text(),'Watch Demo')]")));
        Assert.assertTrue(demo.isDisplayed());
    }

    @Test
    public void testHowItWorksNavigation() {
        driver.get(BASE_URL);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement howIt = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'How it Works')]")));
        howIt.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("nexabi"));
    }
}