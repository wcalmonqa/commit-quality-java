package myplaywrightjava;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import java.io.IOException;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import myplaywrightjava.pages.AddProductPage;
import utils.CommonActions;

public class AddProductPageTests {

  static Playwright playwright;
  static Browser browser;

  BrowserContext context;
  Page page;

  AddProductPage addProductPage;
  CommonActions actions;

  @BeforeClass
  static void launchBrowser(){
    playwright = Playwright.create();
    browser = playwright.chromium().launch();
  }

  @AfterClass
  static void closeBrowser(){
    playwright.close();
  }

  @BeforeMethod
  void createContextAndPage(){
    context = browser.newContext();
    page = context.newPage();

    actions = new CommonActions(page);
    addProductPage = new AddProductPage(page);    
    addProductPage.navigate();
  }

  @AfterMethod
  void closeContext(ITestResult result) throws IOException{
    if(result.getStatus() == ITestResult.FAILURE){
      actions.captureScreenshot();
    }
    context.close();
  }

  @Test
  void addingProductCorrectly(){
    addProductPage.fillProductDataForm("TestProduct", "19.99", "2025-02-20");

    assertThat(page).hasURL("https://commitquality.com/");
  }  

  @Test
  void addingProductWithoutName(){
    addProductPage.fillProductDataForm("", "12.23", "2025-02-20");

    Locator nameErrorMessage = actions.filterLocatorByText(".error-message", "Name"); 

    assertThat(nameErrorMessage).hasText("Name must be at least 2 characters.");
  }

  @Test
  void addingProductWithoutPrice() {
    addProductPage.fillProductDataForm("Product X", "", "2024-06-18");

    Locator priceErrorMessage = actions.filterLocatorByText(".error-message", "Price");

    assertThat(priceErrorMessage).hasText("Price must not be empty and within 10 digits");
  }

  @Test
  void addingProductWithoutDate(){
    addProductPage.fillProductDataForm("Product Test", "24.65", " ");

    Locator dateErrorMessage = actions.filterLocatorByText(".error-message", "Date");

    assertThat(dateErrorMessage).hasText("Date must not be empty.");
  }
 
}