package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
public class HomePage {
	WebDriver driver;


	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	By sizes = By.xpath("//span[@class='checkmark']");
	By totalProd = By.xpath("//small[@class='products-found']/span");
	By orderBy = By.xpath("//div[@class='sort']/select");
	By firstItem = By.xpath("(//p[@class='shelf-item__title'])[1]");

	
	

	public void selectSizes() throws InterruptedException {

		List<WebElement> ele = driver.findElements(sizes);
		for(WebElement it:ele) {
			it.click();
			Thread.sleep(2000);
			String prod = driver.findElement(totalProd).getText();
			System.out.println(prod);		

		}
	}

	public void SelectOrderBy(String order) {
		
		WebElement ele = driver.findElement(orderBy);
		Select sel = new Select(ele);
		sel.selectByVisibleText(order);
		
		
	}
	
	public String nameOfFirstItem() {		
		
		return driver.findElement(firstItem).getText();	
		
	}
	
	public void addItemInCart(int whichItem) {
		
		driver.findElement(By.xpath("(//div[contains(text(),'Add to cart')])["+whichItem+"]")).click();
	}
	
	




}
