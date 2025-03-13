package utils;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import io.qameta.allure.Attachment;

public class CommonActions {
  private final Page page;

  public CommonActions(Page page){
    this.page = page;
  }

  public Locator filterLocatorByText(String selector, String text){
    return this.page.locator(selector).filter(new Locator.FilterOptions().setHasText(text));
  }

  @Attachment(value = "evidence.png", type = "image/png", fileExtension = ".png")
  public byte[] captureScreenshot() {
    return page.screenshot();
  }
}
