package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;

import static com.shop.pages.PageFactory.at;

public class ProductPage extends BasePage {

    private static final By ADD_TO_CART_BUTTON = By.xpath("//button[@data-hook='add-to-cart']");
    private static final By PRODUCT_PAGE_FRAME = By.xpath("//iframe[@title='Product Page']");

    public ProductPage() {
        super();
        switchToFrame(PRODUCT_PAGE_FRAME, WaitCondition.visible);
    }

    @Override
    public NavigationBar withNavigationBar() {
        return at(NavigationBar.class);
    }

    public CartPopUp addToCart() throws InterruptedException {
        //TODO: remove this shit
        Thread.sleep(1000);
        click(ADD_TO_CART_BUTTON, WaitCondition.enabled);
        return at(CartPopUp.class);
    }
}
