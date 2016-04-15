package com.excilys.formation.test.selenium;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TestDashTocreate {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
	driver = new FirefoxDriver();
	 baseUrl = "http://localhost:8080/computer-database/computer";
	//baseUrl = "http://localhost:8080/computer-database-1.0-SNAPSHOT";
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testDashcreate() throws Exception {
	driver.get(baseUrl + "/dashboard");
	driver.findElement(By.id("addComputer")).click();
	assertTrue(driver.getCurrentUrl().equals((baseUrl + "/add")));
    }

    @Test
    public void testDashList100() throws Exception {
	driver.get(baseUrl + "/dashboard?Page=1");
	driver.findElement(By.linkText("100")).click();
	int b = driver.findElements(By.cssSelector("tr[class='computer']")).size();
	assertTrue(b == 100);
    }

    @Test
    public void testDashList50() throws Exception {
	driver.get(baseUrl + "/dashboard?Page=1");
	driver.findElement(By.linkText("50")).click();
	int b = driver.findElements(By.cssSelector("tr[class='computer']")).size();
	assertTrue(b == 50);
    }

    @Test
    public void testcreateDateFalse() throws Exception {
	driver.get(baseUrl + "/dashboard");
	driver.findElement(By.id("addComputer")).click();
	driver.findElement(By.id("introduced")).clear();
	driver.findElement(By.id("introduced")).sendKeys("cjhcjchjv");
	// assertTrue(!driver.findElement(By.id("submit")).isEnabled());
    }

    @Test
    public void testMenuPager() throws Exception {
	driver.get(baseUrl + "/dashboard?Nb=10");
	driver.findElement(By.linkText("4")).click();
	driver.findElement(By.linkText("5")).click();
	driver.findElement(By.linkText("6")).click();
	driver.findElement(By.linkText("7")).click();
	driver.findElement(By.linkText("8")).click();
	driver.findElement(By.linkText("9")).click();
	driver.findElement(By.linkText("10")).click();
	driver.findElement(By.linkText("11")).click();
	driver.findElement(By.linkText("12")).click();
	driver.findElement(By.linkText("13")).click();
	driver.findElement(By.linkText("14")).click();
	driver.findElement(By.linkText("15")).click();
	driver.findElement(By.linkText("16")).click();
	driver.findElement(By.linkText("17")).click();
	driver.findElement(By.linkText("18")).click();
	driver.findElement(By.linkText("19")).click();
	driver.findElement(By.linkText("«")).click();
	driver.findElement(By.linkText("4")).click();
	driver.findElement(By.linkText("5")).click();
	driver.findElement(By.linkText("10")).click();
	driver.findElement(By.linkText("50")).click();
	driver.findElement(By.linkText("3")).click();
    }

    @After
    public void tearDown() throws Exception {
	driver.quit();
	String verificationErrorString = verificationErrors.toString();
	if (!"".equals(verificationErrorString)) {
	    fail(verificationErrorString);
	}
    }

    private boolean isElementPresent(By by) {
	try {
	    driver.findElement(by);
	    return true;
	} catch (NoSuchElementException e) {
	    return false;
	}
    }

    private boolean isAlertPresent() {
	try {
	    driver.switchTo().alert();
	    return true;
	} catch (NoAlertPresentException e) {
	    return false;
	}
    }

    private String closeAlertAndGetItsText() {
	try {
	    Alert alert = driver.switchTo().alert();
	    String alertText = alert.getText();
	    if (acceptNextAlert) {
		alert.accept();
	    } else {
		alert.dismiss();
	    }
	    return alertText;
	} finally {
	    acceptNextAlert = true;
	}
    }
}
