package myplaywrightjava;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;
import com.microsoft.playwright.*;

import org.junit.jupiter.api.*;
import java.nio.file.Paths;

public class AddProductPageTests {

  static Playwright playwright;
  static Browser browser;

  BrowserContext context;
  Page page;

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
  }

  @AfterEach
  void closeContext(){
    context.close();
  }

  @Test
  void addProduct(){
    AddProductPage addProductPage = new AddProductPage(page);
    addProductPage.navigate();
    addProductPage.addProduct("TestProduct", "19.99", "2025-02-20");
    assertThat(page).hasURL("https://commitquality.com/");
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("success1.png")));
  }  

  @Test
  void addProduct2(){
    AddProductPage addProductPage = new AddProductPage(page);
    addProductPage.navigate();
    addProductPage.addProduct("TestProduct2", "19.99", "2025-02-20");
    Assertions.assertEquals( "https://commitquality.com/",page.url());
    page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get("success2.png")));
  }
}