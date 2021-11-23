package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Cart {
	WebDriver driver;


	public Cart(WebDriver driver) {
		this.driver = driver;
	}

	By cancel = By.xpath("//div[contains(text(),'X')]");
	By cart = By.xpath("//div[@class='float-cart']/span");
	By itemsInCart = By.xpath("//span[@class='bag__quantity']");
	By checkout = By.className("buy-btn");
	String increaseItem = "button[contains(text(),'+')])[";
	String decreaseItem = "button[contains(text(),'-')])[";
	String removeItem = "div[@class='shelf-item__del'])[";



	public void cancelCart() {
		driver.findElement(cancel).click();

	}
	

	public void clickOnCart() {
		driver.findElement(cart).click();
	}
	
	
	public String totalItemsInCart() {
		
		return driver.findElement(itemsInCart).getText();
	}

	
	public void increaseQuantity(int whichItem) throws InterruptedException {
		Thread.sleep(3000);

		driver.findElement(By.xpath("(//"+increaseItem+whichItem+"]")).click();
	}
	

	public void decreaseQuantity(int whichItem) {

		driver.findElement(By.xpath("(//"+decreaseItem+whichItem+"]")).click();
	}


	public void removeItem(int whichItem) {

		driver.findElement(By.xpath("(//"+removeItem+whichItem+"]")).click();
	}
	
	
	public void checkoutButton() {
		
		driver.findElement(checkout).click();
	}
	
	
	public String findPriceInAlert() {
		
		return driver.switchTo().alert().getText();
	}
	
	
	public void acceptPrice() {
		
		driver.switchTo().alert().accept();
	}
	
	
	public String getTextOfCheckout() {
		
		return driver.findElement(checkout).getText();
		
	}

}
