

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.*;

import myplaywrightjava.AddProductPage;

import org.junit.jupiter.api.*;
import java.nio.file.Paths;

public class AddProductPageTests {

  static Playwright playwright;
  static Browser browser;

  BrowserContext context;
  Page page;

  AddProductPage addProductPage;

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

    addProductPage = new AddProductPage(page);
    addProductPage.navigate();
  }

  @AfterEach
  void closeContext(){
    context.close();
  }

  @Test
  void addProduct(){
    addProductPage.addProduct("TestProduct", "19.99", "2025-02-20");
    assertThat(page).hasURL("https://commitquality.com/");
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("success1.png")));
  }  

  @Test
  void tryToAddProductWithoutDate(){
    addProductPage.fillNameInput("Product");
    addProductPage.fillPriceInput("11.87");
  }
}