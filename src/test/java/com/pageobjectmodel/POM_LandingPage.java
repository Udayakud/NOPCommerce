package com.pageobjectmodel;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.libglobal.LibGlobal;

public class POM_LandingPage extends LibGlobal {
		
		public POM_LandingPage() {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//*[@id=\"en-page\"]/body/div[7]/header/nav/div[3]/div/ul[1]/li[1]/span")
		private WebElement product;
		@FindBy(xpath="//*[@id=\"en-page\"]/body/div[7]/header/nav/div[3]/div/ul[1]/li[1]/ul/li")
		private List <WebElement> productOptions;
		@FindBy(xpath="//span[text()='My account ']")
		private WebElement myAccount;
		@FindBy(xpath="//span[text()='Log out']")
		private WebElement logOut;

		
		public WebElement getProduct() {
			return product;
		}		
		public List <WebElement> getProductOptions() {
			return productOptions;
		}			
		public WebElement getMyAccount() {
			return myAccount;
		}
		public WebElement getLogOut() {
			return logOut;
		}
	}

