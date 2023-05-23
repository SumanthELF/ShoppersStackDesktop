package TestCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import generic_Library.BaseClass;

public class TC_E2E_22 extends BaseClass {

	@Test
	public void TestCase() throws InterruptedException {
		// Step 4 : Click on 'KIDS' category and click on 'SHIRTS' subcategory
		Actions actions = new Actions(driver);
		WebElement womenNavBarElement = driver.findElement(By.linkText("KIDS"));
		actions.moveToElement(womenNavBarElement).perform();
		Thread.sleep(1000);
		WebElement topsElement = driver.findElement(By.linkText("SHIRTS"));
		Thread.sleep(1000);
		actions.click(topsElement).perform();
		Thread.sleep(1000);
		actions.moveToElement(driver.findElement(By.linkText("Boys Shirt"))).perform();

		// Step 5 : Click on first item's 'Heart' icon
		String firstProductInfo = driver.findElement(By.xpath("(//div[contains(@class,'productCard')])[1]//div[contains(@class,'featuredProducts_cardFooter')]//span")).getText();
		WebElement favoriteIcon = driver.findElement(By.xpath("//*[name()='svg' and @data-testid='FavoriteIcon']"));
		String favoriteIconColorBeforeClick = favoriteIcon.getCssValue("fill");
		Reporter.log("Favourite Icon color before click is " + favoriteIconColorBeforeClick, true);
		Thread.sleep(1000);
		favoriteIcon.click();
		Thread.sleep(1000);
		String favoriteIconColorAfterClick = favoriteIcon.getCssValue("fill");
		Reporter.log("Favourite Icon color before click is " + favoriteIconColorAfterClick, true);

		// Step 6 : Click on "Account settings" icon
		driver.findElement(By.xpath("//button[@aria-label='Account settings']")).click();

		// Step 7 : Click on 'Wish List' link
		driver.findElement(By.xpath("//li[text()='Wish List']")).click();
		Thread.sleep(2000);
		explicitWait.until(ExpectedConditions.urlContains("wishlist"));
		String actualWishListPageTitle = driver.getTitle();
		Assert.assertEquals(actualWishListPageTitle, expectedWishListPageTitle, "WishList page is not displayed");
		Reporter.log("WishList page is displayed", true);
		if(driver.findElement(By.xpath("//h2[contains(text(),'"+firstProductInfo+"')]")).isDisplayed()) {
			Reporter.log("Product added to whishlist is displayed", true);
		}else {
			Reporter.log("Product added to whishlist is not displayed", true);
		}
		
		//Step 8 : Click on 'REMOVE FROM WISHLIST?' button
		driver.findElement(By.xpath("//button[text()='remove from wishlist?']")).click();
		
		//Step 9 : Click on 'OK' button
		explicitWait.until(ExpectedConditions.alertIsPresent());
		Alert confirmationAlert = driver.switchTo().alert();
		Reporter.log("Confirmation dialog box is displayed with follwoing text : "+confirmationAlert.getText(),true);
		Thread.sleep(1000);
		confirmationAlert.accept();
		if(driver.findElement(By.xpath("//h2[contains(text(),'"+firstProductInfo+"')]")).isDisplayed()) {
			Reporter.log("Product added to whishlist is removed", true);
		}else {
			Reporter.log("Product added to whishlist is not removed", true);
		}
	}

}
