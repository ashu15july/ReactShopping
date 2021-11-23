/*
 * This class contains below test cases which not only covers the functionality of Cart page but interacts with
 * HomePage as well.
 * TC1- Verify if user can open and close the cart.
 * TC2- Validate whether user can increase and decrease the item's quantity in cart.
 * TC3- Verify item's count in cart once browser is refreshed. 
 * TC4- Check the checkout functionality of application.
 */

package testCases;

import java.io.File;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import pages.Cart;
import pages.HomePage;
import utilities.ConfigFileReader;

public class TC002_CartValidation {

	WebDriver driver;
	ConfigFileReader conf;
	HomePage hp;
	Cart ct;

	@Parameters({ "browser" })
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
	 * This test case first click on cart icon and verify if cart got open then close it.
	 */
	@Test(priority=0)
	public void cartFunctionality() throws InterruptedException {
		ct.clickOnCart();
		Thread.sleep(2000);
		String str = ct.getTextOfCheckout();
		Assert.assertEquals(str, "CHECKOUT");
		ct.cancelCart();

	}
	
	
	/*
	 * TEST CASE 2
	 * This case adds 2 items in cart then increase the quantity of first item. After that decrease the quanity 
	 * of same item and remove the whole item.
	 * In next step, it verifies total item in cart.
	 */
	@Test(priority=1)
	public void updateItemsInCart() throws InterruptedException {
		hp.addItemInCart(1);
		hp.addItemInCart(2);
		ct.increaseQuantity(1);
		ct.decreaseQuantity(1);
		ct.removeItem(1);
		Thread.sleep(2000);
		String items = ct.totalItemsInCart();
		Assert.assertEquals(items, "1");

	}
	
	
	/*
	 * TEST CASE 3
	 * This case first refresh the browser then open cart.
	 * In next step, it verifies total item in cart.
	 *  
	 */
	@Test(priority=2)
	public void numberOfItemsInCartAfterBrowserRefresh() throws InterruptedException {
		driver.navigate().refresh();
		ct.clickOnCart();
		Thread.sleep(2000);
		String items = ct.totalItemsInCart();
		//Deliberately passing value as 2 to fail this case.
		Assert.assertEquals(items, "2");


	}
	
	
	/*
	 * TEST CASE 4
	 * This case click on checkout button then verifies the alert message.
	 * In next step, it clicks on ok in alert.
	 */
	@Test(priority=3)
	public void checkout() {
		ct.checkoutButton();
		String totalPrice = ct.findPriceInAlert();
		Assert.assertEquals(totalPrice, "Checkout - Subtotal: $ 29.45");
		ct.acceptPrice();
	}
	
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
	@AfterTest
	public void closeBrowser() {		
		driver.close();
	}
}
