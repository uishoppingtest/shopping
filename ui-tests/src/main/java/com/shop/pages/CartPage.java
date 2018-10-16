package com.shop.pages;

import com.shop.utils.WaitCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.shop.asserts.CartAsserts.checkCartSize;

public class CartPage extends BasePage {

    private static final By CART_PAGE_FRAME = By.xpath("//iframe[@title='Cart Page']");
    private static final By CART_CONTENT = By.xpath("//div[@data-hook='cart-content']");
    private static final String PRODUCT_ITEM = "//h3[contains(text(), '%s')]/ancestor::section[@data-hook='item']";
    private static final String PRODUCT_QUANTITY = PRODUCT_ITEM + "//div[@data-hook='product-quantity-input']//input";
    private static final String REMOVE_PRODUCT = PRODUCT_ITEM + "//button[@data-hook='remove-button']";

    public CartPage() {
        super();
        switchToFrame(CART_PAGE_FRAME, WaitCondition.visible);
        isOpen();
    }

    @Step("For product {productName} set quantity {quantity}")
    public CartPage setProductQuantity(String productName, int quantity) {
        By quantityInput = By.xpath(String.format(PRODUCT_QUANTITY, productName));
        cleanAndTypeText(quantityInput, String.valueOf(quantity));
        return this;
    }

    @Step("Renome product {productName} from cart")
    public CartPage removeProduct(String productName) {
        By product = By.xpath(String.format(REMOVE_PRODUCT, productName));
        click(product);
        waitForInvisibility(product);
        return this;
    }

    @Step("Should be {expectedAmount} products {productName} in cart")
    public CartPage shouldHaveInCart(String productName, int expectedAmount) {
        By productQuantity = By.xpath(String.format(PRODUCT_QUANTITY, productName));
        int actualAmountOfProductsInCart = isElementPresent(productQuantity) ?
                Integer.valueOf(find(productQuantity).getAttribute("value")) : 0;
        checkCartSize(actualAmountOfProductsInCart, expectedAmount);
        return this;
    }

    @Override
    public void isOpen() {
        find(CART_CONTENT);
    }
}
