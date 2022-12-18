package com.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.libglobal.LibGlobal;

public class POM_HomePage extends LibGlobal {
		
		public POM_HomePage() {
			PageFactory.initElements(driver, this);
		}
		
		@FindBy(xpath="//span[text()='My account ']")
		private WebElement myAccount;
		@FindBy(xpath="//span[text()='Log in']")
		private WebElement login;
		@FindBy(xpath="//input[@id='Username']")
		private WebElement userName;
		@FindBy(xpath="//input[@id='Password']")
		private WebElement password;
		@FindBy(xpath="//input[@type='submit']")
		private WebElement submit;
		
		public WebElement getSubmit() {
			return submit;
		}		
		public WebElement getPassword() {
			return password;
		}			
		public WebElement getUserName() {
			return userName;
		}	
		public WebElement getLogin() {
			return login;
		}		
		public WebElement getMyAccount() {
			return myAccount;
		}
	}

