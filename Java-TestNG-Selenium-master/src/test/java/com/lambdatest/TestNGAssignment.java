package com.lambdatest;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TestNGAssignment {

    private RemoteWebDriver driver;
    private String Status = "failed";

    @BeforeMethod
    public void setup(Method m, ITestContext ctx) throws MalformedURLException {
        String username = System.getenv("LT_USERNAME") == null ? "Your LT Username" : System.getenv("LT_USERNAME");
        String authkey = System.getenv("LT_ACCESS_KEY") == null ? "Your LT AccessKey" : System.getenv("LT_ACCESS_KEY");;
        String hub = "@hub.lambdatest.com/wd/hub";

        DesiredCapabilities capabilities = new DesiredCapabilities();
       
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("build", "TestNG With Java");
        capabilities.setCapability("name", m.getName() + " - " + this.getClass().getName())

        capabilities.setCapability("platform", "Windows 10");
        capabilities.setCapability("browserName", "Chrome");
        capabilities.setCapability("version","89.0");

        String[] Tags = new String[] { "Feature", "Falcon", "Severe" };
        capabilities.setCapability("tags", Tags);

        driver = new RemoteWebDriver(new URL("https://" + username + ":" + authkey + hub), caps);

    }

    @Test
    public void basicTest() throws InterruptedException {
        String spanText;
        System.out.println("Loading Url");

        //Open URL - https://www.lambdatest.com/selenium-automation
        driver.get("https://www.lambdatest.com/selenium-automation");

        //Maximizes the browser window
        driver.manage().window().maximize() ;

         // Wait until submit button loaded
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("button[type='submit']")));

        // CICD element locator
        WebElement CICD = driver.findElement(By.xpath("//body/div[2]/section[7]/div/div/div[2]/div/div[4]/div[1]"));
        CICD.click();

        //Now click on Learnmore Link
        WebElement LearnMore = driver.findElement(By.xpath("/html/body/div[2]/section[7]/div/div/div[2]/div/div[4]/div[2]/span/a"));
        LearnMore.click();

        //URL is same or not
        string actualURL = "https://www.lambdatest.com/support/docs/integrations-with-ci-cd-tools/";
        string expectedURL = deiver.getCurrentURL();
        Assert.assertEquals(actualURL, expectedURL);

        //Scroll Down and Up
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,1000)");
        js.executeScript("window.scrollBy(1000,0)");

        //Close driver
        driver.close();

        //open URL again
        driver.get("https://www.lambdatest.com/selenium-automation");

   
        //Save Element of Resources, news latter
        WebElement ResourceMenu = driver.findElement(By.cssSelector("#navbarSupportedContent > ul > li.nav-item.dropdown.rs-dropdown > a"));
        WebElement NewsLaterDropdown = driver.findElement(By.cssSelector("#navbarSupportedContent > ul > li.nav-item.dropdown.rs-dropdown > div > a:nth-child(5)"));

        //click on newslatter option from resource dropdown
        Actions action =    new Actions(driver);
        action.moveToElement(ResourceMenu).perform();
        Thread.sleep(2000);
        action.click(NewsLaterDropdown).perform();

        // Click on Let me read it 1st
        WebElement letMeReadIt = driver.findElement(By.cssSelector("body > section.newsletter-home > div > div > div:nth-child(1) > div > div > a"));

        if( driver.findElement(By.cssSelector("a > font")).isDisplayed()){

        System.out.println("Element is Visible");

        }else{

        System.out.println("Element is InVisible");

        }        

        String allEditTextFromLocator = driver.findElementByXPath("//body/div[2]/section[1]/div/div/div/h1").getText();
        Assert.assertEquals("ALL EDITIONS", allEditTextFromLocator);
        Status = "passed";
        Thread.sleep(150);
        System.out.println("TestFinished");

    }

    @AfterMethod
    public void tearDown() {
        driver.executeScript("lambda-status=" + Status);
        driver.quit();
    }

}