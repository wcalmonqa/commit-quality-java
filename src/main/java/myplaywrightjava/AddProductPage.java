package myplaywrightjava;
import com.microsoft.playwright.*;
import com.microsoft.playwright.options.AriaRole;

public class AddProductPage {
    private final Page page;
    private final Locator nameInput;
    private final Locator priceInput;
    private final Locator dateStockedInput;
    private final Locator submitButton;
    private final String URL = "https://commitquality.com/add-product";

    public AddProductPage(Page page){
        this.page = page;
        this.nameInput = page.getByPlaceholder("Enter a product name");
        this.priceInput = page.getByPlaceholder("Enter a price");
        this.dateStockedInput = page.locator("#dateStocked");
        this.submitButton = page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Submit"));
    }

    public void navigate(){
        page.navigate(URL);
    }

    public void fillNameInput(String name){
        nameInput.fill(name);
    }

    public void fillPriceInput(String price){
        priceInput.fill(price);
    }

    public void fillDateStockedInput(String date){
        dateStockedInput.fill(date);
    }

    public void clickOnSubmitButton(){
        submitButton.click();
    }
    public void addProduct(String name, String price, String date){
        fillNameInput(name);
        fillPriceInput(price);
        fillDateStockedInput(date);
        clickOnSubmitButton();
    }
}