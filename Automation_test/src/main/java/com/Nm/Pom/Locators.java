package com.Nm.Pom;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Nm.Base.BaseClass;



public class Locators extends BaseClass {
	public Locators() {
		PageFactory.initElements(driver, this);
		
	}
	
	//*********************************************************M3 Flow starts********************************************************************************
	
	@FindBy(id = "s-result-sort-select_2")
	private WebElement price_highlow;
	public WebElement getprice_highlow() {
		return price_highlow;
	}
	
	@FindBy(xpath ="//i[@class='hm-icon nav-sprite']")
	private WebElement Hamburger;
	public WebElement getHamburger() {
		return Hamburger;
	}
	//div[.='TV, Appliances, Electronics']
	
	
	@FindBy(xpath ="//div[contains(text(),'TV, Appliances, Electronics')]")
	private WebElement Tvandapplicances;
	public WebElement getTvandapplicances() {
		return Tvandapplicances;
	}

	@FindBy(xpath ="//span[text()='Brands']")
	private WebElement Brands;
	public WebElement getBrands() {
		return Brands;
	}
	
	
	
	@FindBy(xpath ="//span[text()='Featured']")
	private WebElement Featured;
	public WebElement getFeatured() {
		return Featured;
	}
	
	
}
