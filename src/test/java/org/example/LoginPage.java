package org.example;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

public class LoginPage {

	@FindBy(name="username")
	private WebElement username;
	
	@FindBy(name="password")
	private WebElement password;
	
	@FindBy(id="authenticate")
	private WebElement authenticateButton;
	
	@FindBy(id="account")
	private WebElement accountPanel;

	private final WebDriver driver;
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
	public static LoginPage open(WebDriver driver) {
		return PageFactory.initElements(driver, LoginPage.class);
	}

	public void setUsername(String username) { 
		this.username.sendKeys(username); 
	}

	public void setPassword(String password) { 
		this.password.sendKeys(password);
	}
	
	public String welcomeMessage() {
		return accountPanel.getText();
	}
	
	public void clickAuthenticate() {
		this.authenticateButton.click();
		new WebDriverWait(driver, 30).until(accountPanelIsVisible());
	}
	
	private Predicate<WebDriver> accountPanelIsVisible() {
		return new Predicate<WebDriver>() {
			@Override public boolean apply(WebDriver driver) {
				return isAccountPanelVisible();
			}
		};
	}

	private boolean isAccountPanelVisible() {
		return accountPanel.isDisplayed();
	}
	
	
	public void authenticate() {
		this.authenticateButton.click();
		new WebDriverWait(driver, 30).until(authenticated());
	}
	
	private Predicate<WebDriver> authenticated() {
		return new Predicate<WebDriver>() {
			@Override public boolean apply(WebDriver driver) {
				return isAuthenticated();
			}
			
		};
	}
	
	private boolean isAuthenticated() {
		return (Boolean) executor().executeScript("return authenticated;");
	}

	private JavascriptExecutor executor() {
		return (JavascriptExecutor) driver;
	}
}
