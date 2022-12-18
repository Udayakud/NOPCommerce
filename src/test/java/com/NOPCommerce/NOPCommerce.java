package com.NOPCommerce;

import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.etsi.uri.x01903.v13.impl.GenericTimeStampTypeImpl;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.libglobal.LibGlobal;
import com.pageobjectmodel.POM_HomePage;
import com.pageobjectmodel.POM_LandingPage;

public class NOPCommerce extends LibGlobal {
	@BeforeTest
	private void launchBrowser() {
		launchBrowser("chrome");
	}
	@Test (priority=1)
	private void homePage() {
		launchUrl("https://www.nopcommerce.com/en");
		String actualTitle ="Free and open-source eCommerce platform. ASP.NET Core based shopping cart. - nopCommerce";
		Assert.assertEquals(title(), actualTitle);
	}
	@Test (priority=2)
	private void myAccount() {
		POM_HomePage h = new POM_HomePage();
		WebElement myAccount = h.getMyAccount();
		click(myAccount);
		String actualTitle ="Free and open-source eCommerce platform. ASP.NET Core based shopping cart. - nopCommerce";
		Assert.assertEquals(title(), actualTitle);
	}
	@Test (priority=3)
	private void logIn() {
		POM_HomePage h = new POM_HomePage();
		WebElement login = h.getLogin();
		Assert.assertEquals(isEnabled(login), true );
		click(login);	
	}
	@Test (priority=4)
	private void logInValidation() {
		POM_HomePage h = new POM_HomePage();
		WebElement userName = h.getUserName();
		sendKeys(userName, "udayakumar.1990@gmail.com");
		WebElement password = h.getPassword();
		sendKeys(password, "Future@123");
		WebElement submit = h.getSubmit();
		click(submit);
//		System.out.println(title());
	}
	@Test (priority=5)
	private void landingPage() {
		POM_LandingPage l = new POM_LandingPage();
		WebElement product = l.getProduct();
		click(product);
	}
	@Test (priority=6)
	private void productOptionsStoreDemo() {
		POM_LandingPage l = new POM_LandingPage();
		List<WebElement> productOptions = l.getProductOptions();
		WebElement webElement = productOptions.get(0);
		String text = webElement.getText();	
		assertEquals(text, "Store demo");
	}
	@Test (priority=7)
	private void productOptionsShowcase() {
		POM_LandingPage l = new POM_LandingPage();
		List<WebElement> productOptions = l.getProductOptions();
		WebElement webElement = productOptions.get(1);
		String text = webElement.getText();	
		assertEquals(text, "Showcase");
	}
	@Test (priority=8)
	private void productOptionsFeatures() {
		POM_LandingPage l = new POM_LandingPage();
		List<WebElement> productOptions = l.getProductOptions();
		WebElement webElement = productOptions.get(2);
		String text = webElement.getText();	
		assertEquals(text, "Features");
	}
	@Test (priority=9)
	private void productOptionsWhyfordevelopers() {
		POM_LandingPage l = new POM_LandingPage();
		List<WebElement> productOptions = l.getProductOptions();
		WebElement webElement = productOptions.get(3);
		String text = webElement.getText();	
		assertEquals(text, "Why for developers");
	}
	@Test (priority=10)
	private void productOptionsWhyforstoreowners() {
		POM_LandingPage l = new POM_LandingPage();
		List<WebElement> productOptions = l.getProductOptions();
		WebElement webElement = productOptions.get(4);
		String text = webElement.getText();	
		assertEquals(text, "Why for store owners");
	}
	@Test (priority=11)
	private void productOptionsIndustries() {
		POM_LandingPage l = new POM_LandingPage();
		List<WebElement> productOptions = l.getProductOptions();
		WebElement webElement = productOptions.get(5);
		String text = webElement.getText();	
		assertEquals(text, "Industries");
	}
	@Test (priority=12)
	private void logOutCheck() {
		POM_LandingPage l = new POM_LandingPage();
		WebElement myAccount = l.getMyAccount();
		click(myAccount);
		WebElement logOut = l.getLogOut();
		click(logOut);
	}
}



