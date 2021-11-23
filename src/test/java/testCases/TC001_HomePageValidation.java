/*
 * This class contains the below test cases for HomePage of application. Here test cases are designed using Testng framework.
 * TC1- Verify whether user can select and deselect all the sizes filter and relevant items appear on page 
 * once filter is selected/deselected. 
 * TC2- Check if user is allowed to select dropdown values from Order By filter and items appear according to selection.
 * TC3- Verify if user can add items in cart from HomePage by clicking on Add to cart button.
 */


package testCases;

import org.testng.annotations.Test;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.AssertJUnit;
import org.testng.ITestResult;
import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Parameters;
import pages.Cart;
import pages.HomePage;
import utilities.ConfigFileReader;

public class TC001_HomePageValidation {
	
	WebDriver driver;
	ConfigFileReader conf;
	HomePage hp;
	Cart ct;	
	
	//This annotation will read the browser's value from Testng.xml file and test execution will happen on same browser.
	@Parameters({ "browser" })
	
	/*
	 * This is more of test preparation step where execution will pick browser according to user's input and
	 * open the URL provided in Config/Configuration.properties file.	 * 
	*/
	@BeforeTest
	public void setUp(String browser) throws InterruptedException {
		conf = new ConfigFileReader();
		try {
			if (browser.equalsIgnoreCase("Firefox")) {
				System.setProperty("webdriver.gecko.driver",conf.getFirefoxDriverPath());
				driver = new FirefoxDriver();
			} else if (browser.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",conf.getChromeDriverPath());
				driver = new ChromeDriver();
			}
		
		} catch (WebDriverException e) {
			System.out.println(e.getMessage());
		}
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		driver.get(conf.getApplicationUrl());
		driver.manage().window().maximize();
		hp = new HomePage(driver);
		ct = new Cart(driver);
	}
	
	
	/* 
	 * TEST CASE 1
	 * This is first test case where system will check selection of each size one by one and print out corresponding 
	 * items. Later, system will verify if user can deselect same size filters as well. 
	 */
	@Test(priority=0)
	public void verifySizesFilter() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);		
		hp.selectSizes();
		hp.selectSizes();
	
	}
	
	
	/*	
	 * TEST CASE 2
	 * The purpose of this test to verify whether user can select order by drop down's value and items appear according to 
	 * selection or not.	
	*/
	@Test(priority=1)
	public void verifyOrderBySorting() throws InterruptedException {
		hp.SelectOrderBy("Highest to lowest");
		Thread.sleep(2000);
		String highItem = hp.nameOfFirstItem();
		AssertJUnit.assertEquals(highItem, "Crazy Monkey Grey");
		hp.SelectOrderBy("Lowest to highest");
		Thread.sleep(2000);
		String lowItem = hp.nameOfFirstItem();
		AssertJUnit.assertEquals(lowItem, "Sphynx Tie Dye Wine T-Shirt");
		
	}
	
	
	/*
	 * TEST CASE 3
	 * The intention of this test to verify whether user can add items in cart or not. Here user has to pass which 
	 * number of item from the list s/he wants to add and addItemInCart method will take that number as input and 
	 * add that item in cart.	 * 
	 */
	@Test(priority=2)
	public void addItemsInCart() throws InterruptedException {
		Thread.sleep(2000);
		hp.addItemInCart(1);
		hp.addItemInCart(2);
		
	}
	
	/*
	 * This method will be called after each test and check if test case got passed or failed.
	 * in case TC gets failed then screenshot will be taken and stored in Screenshots folder with TC name.
	 */
	@AfterMethod 
	public void screenShot(ITestResult result){
		if(ITestResult.FAILURE==result.getStatus()){
			try 
			{
				TakesScreenshot ts=(TakesScreenshot)driver;

				File source=ts.getScreenshotAs(OutputType.FILE);

				FileHandler.copy(source, new File("./Screenshots/"+result.getName()+".png"));

				System.out.println("Screenshot taken");
			} 
			catch (Exception e)
			{

				System.out.println("Exception while taking screenshot "+e.getMessage());
			} 
		}
	}
	
	
	/*
	 * Once all test cases are executed then this method will be called and close the instance of browser.
	 */
	@AfterTest
	public void closeBrowser() {		
		driver.close();
	}
	
	
	

}
