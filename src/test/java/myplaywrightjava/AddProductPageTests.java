package myplaywrightjava;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

import com.microsoft.playwright.*;

import utils.CommonActions;

import org.junit.jupiter.api.*;
import java.nio.file.Paths;

public class AddProductPageTests {

  static Playwright playwright;
  static Browser browser;

  BrowserContext context;
  Page page;

  AddProductPage addProductPage;
  CommonActions actions;

  @BeforeAll
  static void launchBrowser(){
    playwright = Playwright.create();
    browser = playwright.chromium().launch();
  }

  @AfterAll
  static void closeBrowser(){
    playwright.close();
  }

  @BeforeEach
  void createContextAndPage(){
    context = browser.newContext();
    page = context.newPage();

    actions = new CommonActions(page);
    addProductPage = new AddProductPage(page);    
    addProductPage.navigate();
  }

  @AfterEach
  void closeContext(){
    context.close();
  }

  @Test
  void addingProductCorrectly(){
    addProductPage.fillProductDataForm("TestProduct", "19.99", "2025-02-20");
    assertThat(page).hasURL("https://commitquality.com/");
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("success1.png")));
  }  

  @Test
  void addingProductWithoutName(){
    addProductPage.fillProductDataForm("", "12.23", "2025-02-20");
    Locator nameErrorMessage = actions.filterLocatorByText(".error-message", "Name"); 
    assertThat(nameErrorMessage).hasText("Name must be at least 2 characters.");
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("emptyNameErrorMessage.png")));
  }

  @Test
  void addingProductWithoutPrice(){
    addProductPage.fillProductDataForm("Product X", "", "2024-06-18");

    Locator priceErrorMessage = actions.filterLocatorByText(".error-message", "Price");

    assertThat(priceErrorMessage).hasText("Price must not be empty and within 10 digits");
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("emptyPriceErrorMessage.png")));

  }
}