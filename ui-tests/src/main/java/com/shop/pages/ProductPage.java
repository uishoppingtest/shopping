package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;

import static com.shop.asserts.GeneralPageAsserts.checkText;
import static com.shop.asserts.GeneralPageAsserts.isPageOpen;
import static com.shop.pages.PageFactory.at;

public class ProductPage extends BasePage {

    private static final By ADD_TO_CART_BUTTON = By.xpath("//button[@data-hook='add-to-cart']");
    private static final By PRODUCT_PAGE_FRAME = By.xpath("//iframe[@title='Product Page']");
    private static final By PRODUCT_NAME = By.xpath("//h2[@class='product-name']");
    private static final By PRODUCT_PAGE_CONTAINER = By.xpath("//product-page[@class='product-page-container']");
    private static final By STORES_BREADCRUMPS = By.xpath("//a[text()='STORES']");

    public ProductPage() {
        super();
        switchToFrame(PRODUCT_PAGE_FRAME, WaitCondition.visible);
        isOpen();
    }

    @Override
    public void isOpen() {
        isPageOpen(find(PRODUCT_PAGE_CONTAINER));
    }

    public CartPopUp addToCart() {
        click(ADD_TO_CART_BUTTON);
        return at(CartPopUp.class);
    }

    public ProductPage shouldBeProduct(String productName) {
        checkText(find(PRODUCT_NAME), productName);
        return this;
    }

    public StorePage navigateToStore() {
        click(STORES_BREADCRUMPS);
        return at(StorePage.class);
    }
}
