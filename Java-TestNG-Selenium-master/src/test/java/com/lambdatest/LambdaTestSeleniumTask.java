package com.lambdatest;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LambdaTestSeleniumTask {

	public static void main(String[] args) throws InterruptedException {
		
		
		// Open Chrome browser in Local System
		System.setProperty("webdriver.chrome.driver","C:\\Browser\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		//Step 1 - Open URL
		driver.get("https://www.lambdatest.com/automation-demos/");
		
		//Maximize window
		driver.manage().window().maximize();
		
		//Step 2 - Enter user name and password in respective text fields 
		WebElement userName = driver.findElement(By.id("username"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement loginButton = driver.findElement(By.xpath("//*[@id='applyform']/div/button"));
		userName.sendKeys("lambda");
		password.sendKeys("lambda123");
		Thread.sleep(1000);
		loginButton.submit();
		Thread.sleep(1000);
		
		//Step 3 - Open Form and enter Email address and click populate button
		
		WebElement devEmail = driver.findElement(By.id("developer-name"));
		WebElement populateBtn = driver.findElement(By.id("populate")); boolean
		devEmailDisplayed = devEmail.isDisplayed();
		System.out.println(devEmailDisplayed);
		devEmail.sendKeys("rajasekhar502.yalla@gmail.com"); populateBtn.click();
		 
		//Accept the alert
		Alert alert = driver.switchTo().alert(); 
		alert.accept();
		
		//Fill form 
		driver.findElement(By.xpath("//body/div[2]/section[3]/div/div[1]/div/div[2]/p[1]/label")).click();
		driver.findElement(By.xpath("//*[@id='customer-service']")).click();
		WebElement paymentDropdown = driver.findElement(By.xpath("//*[@id='preferred-payment']"));
		Select paymentSelection = new Select(paymentDropdown);
		paymentSelection.selectByVisibleText("Wallets");
		driver.findElement(By.xpath("//*[@id='tried-ecom']")).click();
				
		//Enable the rating scale and feedback text field from the respective checkbox
		WebElement ratingSlider = driver.findElement(By.xpath("//*[@id='slider']/span"));
		WebElement ratingSliderNine = driver.findElement(By.xpath("//body/div[2]/section[3]/div/div[2]/div[2]/div/div[2]/div[9]"));
		Actions builder = new Actions(driver);   
		builder.moveToElement(ratingSlider)
			   .click().dragAndDrop(ratingSlider, ratingSliderNine)
			   .build()
			   .perform();
		
		driver.findElement(By.id("comments")).sendKeys("Testing Purpose - Please Ignore");
		driver.findElement(By.xpath("//body/div[2]/section[3]/div/div[1]/div/div[3]/p[3]/label")).click();
		// Open new URL in new Tab 
		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +"t");
	    ArrayList<String> tabs = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs.get(1)); //switches to new tab
	    driver.get("https://www.lambdatest.com/selenium-automation");
	        
	    // Wait until email text box 
        WebDriverWait wait = new WebDriverWait(driver,30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("useremail")));

	    //Click on Jenkins
        WebElement jenkinsImage = driver.findElement(By.xpath("//body/div[2]/section[6]/div/div[2]/ul/li[1]/a/img"));       
        String jenkinsImageSource = jenkinsImage.getAttribute("src");
       
        try { 
       
        URL imageURL = new URL(jenkinsImageSource);
        BufferedImage saveImage = ImageIO.read(imageURL);
        ImageIO.write(saveImage, "png", new File("JenkinImage.png"));
        
        } catch (Exception e) {
	        e.printStackTrace();
	     } 
        
        finally {
	        driver.close();
	     }  
        
        WebElement uploadButton = driver.findElement(By.id("submit-button"));
        uploadButton.click();
        uploadButton.sendKeys("C:\\JenkinImage.png");
        
		//Accept the alert
		Alert alertnew = driver.switchTo().alert(); 
		alertnew.accept();
		
		//click on submit
		driver.findElement(By.xpath("//*[@id'submit-button']")).click();   	
			
	}

}
