package myTest;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import static org.testng.AssertJUnit.assertEquals;
import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import dev.failsafe.internal.util.Assert;

public class program1 {
	
	 WebDriver driver;
	//Open the browser go to the URL and accept the cookies
	@BeforeTest
	public void startChrome()
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\chromedriver.exe");
	     driver=new ChromeDriver();
		driver.get("http://www.ii.co.uk/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		//Mouse Hovering to accept cookies
		WebElement cookies=driver.findElement(By.xpath("//button[normalize-space()='Accept']"));
		Actions actions = new Actions(driver); 
		actions.moveToElement(cookies).click().build().perform();
	
	}
	

	//Navigate to 'services' menu,click TradingAccount and assert the URL with expected URL
	@ Test (priority=1)
	public void Services()
	{
		driver.findElement(By.cssSelector("span[title='Services']")).click();
		driver.findElement(By.linkText("Trading Account")).click();;
		String actualURL=driver.getCurrentUrl();
	    String expectedURL="https://www.ii.co.uk/ii-accounts/trading-account";
	    AssertJUnit.assertEquals(actualURL,expectedURL);
	    System.out.println(driver.getCurrentUrl());
	}
	
    //Navigate to 'Pensions',click SIPPcharges and assert the URL with expected URL
	@ Test (priority=2)
	public void Pensions()
	{
		driver.findElement(By.xpath("//span[@title='Pensions']")).click();
		driver.findElement(By.linkText("SIPP charges")).click();;
		String actualURL=driver.getCurrentUrl();
	    String expectedURL="https://www.ii.co.uk/ii-accounts/sipp/sipp-charges";
	    AssertJUnit.assertEquals(actualURL,expectedURL);
		System.out.println(driver.getCurrentUrl());
		//Click Options at retirement on SIPP charges page and print title of the page in console 
		driver.findElement(By.cssSelector("div[class='ii-12ntsty'] div[class='ii-0'] div:nth-child(1)")).click();
		System.out.println("Title of the Options at retirement is --"+driver.getTitle());
	  }
	//Assert the text present on Transeferyourpension button in SIPPcharges with expected text
	@ Test (priority=3)
	public void Button()
	{
	WebElement button=driver.findElement(By.xpath("//span[@title='Transfer your pension']"));	
	String actualText="Transfer your pension"; 
	AssertJUnit.assertEquals(actualText,button.getText());
	System.out.println("Text present on Transfer your pension button is--"+button.getText());
	}
	
		@ AfterTest
		public void cleanUp()
		{
			System.out.println("Browser successfully closed ");
			driver.quit();
		}
		
} 
