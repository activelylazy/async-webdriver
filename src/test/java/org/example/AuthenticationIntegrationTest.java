package org.example;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class AuthenticationIntegrationTest {

	private WebDriver driver;

	@Before
	public void openWebDriver()
	{
		driver = new FirefoxDriver();
	}

	@After
	public void closeWebDriver()
	{
		driver.close();
	}
	
	@Test
	public void authenticatesUser()
	{
		driver.get("http://localhost:8080/");
		
		LoginPage loginPage = LoginPage.open(driver);
		loginPage.setUsername("admin");
		loginPage.setPassword("password");
		loginPage.clickAuthenticate();

		Assert.assertEquals("Logged in as admin", loginPage.welcomeMessage());
	}
	
	@Test
	public void authentication()
	{
		driver.get("http://localhost:8080/");
		
		LoginPage loginPage = LoginPage.open(driver);
		loginPage.setUsername("admin");
		loginPage.setPassword("password");
		loginPage.authenticate();

		Assert.assertEquals("Logged in as admin", loginPage.welcomeMessage());
	}
	
}
