package com.excilys.formation.test.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TestEdit {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/computer-database/computer";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void testEdit() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.linkText("CM-2")).click();
    driver.findElement(By.id("introduced")).clear();
    driver.findElement(By.id("introduced")).sendKeys("2015-02-02");
    driver.findElement(By.id("discontinued")).clear();
    driver.findElement(By.id("discontinued")).sendKeys("2016-02-02");
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("CM-2zadzazdazda");
    new Select(driver.findElement(By.id("companyId"))).selectByVisibleText("RCA");
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    driver.findElement(By.linkText("CM-2zadzazdazda")).click();
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.id("computerName")).sendKeys("CM-2");
    driver.findElement(By.id("introduced")).clear();
    driver.findElement(By.id("introduced")).sendKeys("");
    driver.findElement(By.id("discontinued")).clear();
    driver.findElement(By.id("discontinued")).sendKeys("");
    new Select(driver.findElement(By.id("companyId"))).selectByVisibleText("Thinking Machines");
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    assertTrue(driver.getCurrentUrl().equals(baseUrl + "/dashboard"));
  }

  @Test
  public void testInvalidDate() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.linkText("CM-2")).click();
    driver.findElement(By.id("introduced")).clear();
    driver.findElement(By.id("introduced")).sendKeys("2015-00000002-02");
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    assertTrue(driver.getCurrentUrl().equals(baseUrl + "/edit?id=2"));
  }
  @Test
  public void testInvalidName() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.linkText("CM-2")).click();
    driver.findElement(By.id("computerName")).clear();
    driver.findElement(By.cssSelector("input.btn.btn-primary")).click();
    assertTrue(driver.getCurrentUrl().equals(baseUrl + "/edit?id=2"));
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

