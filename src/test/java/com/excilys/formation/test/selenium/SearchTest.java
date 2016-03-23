package com.excilys.formation.test.selenium;

import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class SearchTest {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/computer-database";
    driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void testEe() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.id("searchbox")).clear();
    driver.findElement(By.id("searchbox")).sendKeys("le");
    driver.findElement(By.id("searchsubmit")).click();
    driver.findElement(By.linkText("2")).click();
    driver.findElement(By.linkText("3")).click();
    driver.findElement(By.linkText("4")).click();
    driver.findElement(By.linkText("5")).click();
  }
  @Test
  public void testOfoun() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.id("searchbox")).clear();
    driver.findElement(By.id("searchbox")).sendKeys("zaezefezfze");
    driver.findElement(By.id("searchsubmit")).click();
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
