package main.java.pages.vlab.core;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import main.java.base.TestBase;

public class UserLabProcedureFirstSession extends TestBase {
	
	private JavascriptExecutor jse = null;

	public UserLabProcedureFirstSession() {
		jse = (JavascriptExecutor) getDriver();
		PageFactory.initElements(new AjaxElementLocatorFactory(getDriver(), 10), this);
	}

}
