package com.shop.pages;

import com.shop.utils.WaitCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.shop.asserts.GeneralPageAsserts.checkText;
import static com.shop.pages.PageFactory.at;

public class ProductPage extends BasePage {

    private static final By ADD_TO_CART_BUTTON = By.xpath("//button[@data-hook='add-to-cart']");
    private static final By PRODUCT_PAGE_FRAME = By.xpath("//iframe[@title='Product Page']");
    private static final By PRODUCT_NAME = By.xpath("//h2[@class='product-name']");
    private static final By STORES_BREADCRUMPS = By.xpath("//a[text()='STORES']");

    public ProductPage() {
        super();
        switchToFrame(PRODUCT_PAGE_FRAME, WaitCondition.visible);
        isOpen();
    }

    @Override
    public void isOpen() {
        find(PRODUCT_NAME);
    }

    @Step("Add product to cart")
    public CartPopUp addToCart() {
        click(ADD_TO_CART_BUTTON);
        return at(CartPopUp.class);
    }

    @Step("Product {productName} should be shown on Product page")
    public ProductPage shouldBeProduct(String productName) {
        checkText(find(PRODUCT_NAME), productName);
        return this;
    }

    @Step("Navigate to store page")
    public StorePage navigateToStore() {
        click(STORES_BREADCRUMPS);
        return at(StorePage.class);
    }
}
