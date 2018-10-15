package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;

import static com.shop.asserts.GeneralPageAssers.checkText;
import static com.shop.asserts.GeneralPageAssers.isPageOpen;
import static com.shop.pages.PageFactory.at;

public class ProductPage extends BasePage {

    private static final By ADD_TO_CART_BUTTON = By.xpath("//button[@data-hook='add-to-cart']");
    private static final By PRODUCT_PAGE_FRAME = By.xpath("//iframe[@title='Product Page']");
    private static final By PRODUCT_NAME = By.xpath("//h2[@class='product-name']");
    private static final By PRODUCT_PAGE_CONTAINER = By.xpath("//product-page[@class='product-page-container']");

    public ProductPage() {
        super();
        switchToFrame(PRODUCT_PAGE_FRAME, WaitCondition.visible);
        isOpen();
    }

    @Override
    public void isOpen() {
        isPageOpen(find(PRODUCT_PAGE_CONTAINER));
    }

    public CartPopUp addToCart() throws InterruptedException {
        //TODO: remove this shit. Clicks on button earlier than it becomes available. But still it's clickable anyway
        Thread.sleep(1000);
        click(ADD_TO_CART_BUTTON);
        return at(CartPopUp.class);
    }

    public ProductPage shouldBeProduct(String productName) {
        checkText(find(PRODUCT_NAME), productName);
        return this;
    }
}
