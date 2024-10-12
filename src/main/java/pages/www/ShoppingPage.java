package main.java.pages.www;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;
import main.java.utils.CommonFunctions;

public class ShoppingPage extends TestBase {

	private JavascriptExecutor jse = null;

	@FindBy(xpath = "//*[text()='Shop Our Products']")
	private WebElement shopPageHeader;

	@FindBy(xpath = "//*[@data-testid='eligibility-check-button']")
	private WebElement orderNow;

	@FindBy(xpath = "//button[text()='Add to Cart']")
	private WebElement addToCart;

	@FindBy(xpath = "//a[contains(@class,'cart-summary')]")
	private WebElement cartIcon;

	@FindBy(xpath = "//p[contains(text(),'$150.00')]//ancestor::a")
	private WebElement sixKitPack;

	@FindBy(xpath = "//p[contains(text(),'25')]//ancestor::a")
	private WebElement singleKitPack;

	public ShoppingPage() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

	/**
	 * This method for verifying the shopping page displayed
	 *
	 *
	 * @throws Exception
	 */
	public void verifyShoppingPage() throws Exception {
		CommonFunctions.checkCurrentPageTitle("Coronavirus At-Home Test Kit");
		CommonFunctions.logMessage("<-----EMed Shopping Page----->");
	}

	/**
	 * This method for selecting the single kit pack
	 *
	 * @throws Exception
	 */

	public void clickSingleKit() throws Exception {
		CommonFunctions.clickWebelement(singleKitPack, "Single kit pack");
	}

	/**
	 * This method for selecting the six kit pack
	 *
	 * @throws Exception
	 */

	public void clickSixKit() throws Exception {
		boolean flag = CommonFunctions.isExist(getDriver(), "//p[contains(text(),'$150.00')]//ancestor::a");
		if (flag) {
			CommonFunctions.clickWebelement(sixKitPack, "Six kit pack");
		}
	}

	/**
	 * This method for click checkQualify
	 *
	 * @throws Exception
	 */

	public void clickCheckQualification() throws Exception {
		CommonFunctions.elementToBeClickable(orderNow, "order now button");
		WebElement checkQualify = getDriver().findElement(By.xpath("//*[@data-testid='eligibility-check-button']"));
		checkQualify.click();
		CommonFunctions.logMessage("Order Now button is clicked");
	}

	/**
	 * This method to click AddToCart
	 *
	 * @throws Exception
	 */

	public void clickAddToCart() throws Exception {
		CommonFunctions.elementToBeClickable(addToCart, "add to cart button");
		WebElement addCart = getDriver().findElement(By.xpath("//button[text()='Add to Cart']"));
		addCart.click();
		CommonFunctions.logMessage("Add to cart button is clicked");
	}

	/**
	 * This method to click cart Icon
	 *
	 * @throws Exception
	 */

	public void cartIcon() throws Exception {
		CommonFunctions.clickWebelement(cartIcon, "cart icon");
	}
}
