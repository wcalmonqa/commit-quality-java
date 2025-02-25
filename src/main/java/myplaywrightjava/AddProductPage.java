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
        this.page.navigate(URL);
    }

    public void fillProductDataForm(String name, String price, String date){
        nameInput.fill(name);
        priceInput.fill(price);
        dateStockedInput.fill(date);
        submitButton.click();
    }
}