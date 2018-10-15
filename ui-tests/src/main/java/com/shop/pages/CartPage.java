package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static com.shop.pages.PageFactory.at;

public class CartPage extends BasePage {

    private static final By CART_PAGE_FRAME = By.xpath("//iframe[@title='Cart Page']");
    private static final String PRODUCT_ITEM = "//h3[contains(text(), '%s')]/ancestor::section[@data-hook='item']";
    private static final String PRODUCT_QUANTITY = PRODUCT_ITEM + "//div[@data-hook='product-quantity-input']";
    private static final String REMOVE_PRODUCT = PRODUCT_ITEM + "//button[@data-hook='remove-button']";

    public CartPage() {
        super();
        switchToFrame(CART_PAGE_FRAME, WaitCondition.visible);
    }

    public CartPage setProductQuantity(String productName, int quantity) {
        By quantityInput = byXPath(String.format(PRODUCT_QUANTITY, productName));
        putText(quantityInput, String.valueOf(quantity));
        return this;
    }

    public CartPage removeProduct(String productName) {
        click(byXPath(String.format(REMOVE_PRODUCT, productName)), WaitCondition.enabled);
        return this;
    }

    @Override
    public NavigationBar withNavigationBar() {
        return at(NavigationBar.class);
    }
}
