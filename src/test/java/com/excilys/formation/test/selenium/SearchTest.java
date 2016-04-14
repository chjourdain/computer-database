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
    int b = driver.findElements(By.cssSelector("tr[class='computer']")).size();
    assertTrue(b ==10);
  }
  @Test
  public void testOfoun() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.id("searchbox")).clear();
    driver.findElement(By.id("searchbox")).sendKeys("zaezezfzeefzeffezfze");
    driver.findElement(By.id("searchsubmit")).click();
    int b = driver.findElements(By.cssSelector("tr[class='computer']")).size();
    assertTrue(b ==0);
  }
  @Test
  public void testMenu() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.id("searchbox")).clear();
    driver.findElement(By.id("searchbox")).sendKeys("a");
    driver.findElement(By.id("searchsubmit")).click();
    driver.findElement(By.linkText("Computer name")).click();
    driver.findElement(By.linkText("Introduced date")).click();
    driver.findElement(By.linkText("Discontinued date")).click();
    driver.findElement(By.linkText("Company")).click();
    driver.findElement(By.linkText("50")).click();
    driver.findElement(By.linkText("Discontinued date")).click();
    driver.findElement(By.linkText("Introduced date")).click();
    driver.findElement(By.linkText("Computer name")).click();
    int b = driver.findElements(By.cssSelector("tr[class='computer']")).size();
    System.out.println(b);
    assertTrue(b ==50);
  }
  @Test
  public void testNbParPage() throws Exception {
    driver.get(baseUrl + "/dashboard");
    driver.findElement(By.id("searchbox")).clear();
    driver.findElement(By.id("searchbox")).sendKeys("e");
    driver.findElement(By.id("searchsubmit")).click();
    int b = driver.findElements(By.cssSelector("tr[class='computer']")).size();
    assertTrue(b ==10);
    driver.findElement(By.linkText("50")).click();
    b = driver.findElements(By.cssSelector("tr[class='computer']")).size();
    assertTrue(b ==50);
    driver.findElement(By.linkText("100")).click();
    b = driver.findElements(By.cssSelector("tr[class='computer']")).size();
    assertTrue(b ==100);
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
