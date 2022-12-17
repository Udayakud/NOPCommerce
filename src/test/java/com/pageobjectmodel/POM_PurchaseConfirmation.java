package com.pageobjectmodel;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.libglobal.LibGlobal;

public class POM_PurchaseConfirmation extends LibGlobal {
	
	public POM_PurchaseConfirmation() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="/html/body/div[2]/div/table/tbody/tr[1]/td[2]")
	private WebElement id;
	@FindBy(xpath="/html/body/div[2]/div/table/tbody/tr[3]/td[2]")
	private WebElement amount;
	@FindBy(xpath="/html/body/div[2]/div/table/tbody/tr[4]/td[2]")
	private WebElement cardNumber;
	
	public WebElement getId() {
		return id;
	}
	public WebElement getAmount() {
		return amount;
	}
	public WebElement getCardNumber() {
		return cardNumber;
	}
		
}
