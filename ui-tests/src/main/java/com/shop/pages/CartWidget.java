package com.shop.pages;

import com.shop.utils.WaitCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.shop.asserts.CartAsserts.checkCartSize;
import static com.shop.pages.PageFactory.at;

public class CartWidget extends BasePage{

    private final By CART_FRAME = By.xpath("//iframe[@title='Wix Stores']");
    private final By CART_BUTTON = By.xpath("//a[@id='cart-widget-button']");
    private final By CART_BUTTON_DATA = By.xpath("//a[@id='cart-widget-button']//*[name()='text']");

    public CartWidget() {
        super();
        switchToFrame(CART_FRAME, WaitCondition.visible);
        isOpen();
    }

    @Override
    public void isOpen() {
        find(CART_BUTTON_DATA);
    }

    @Step("Open cart pop up")
    public CartPopUp navigateToCart() throws InterruptedException {
        //TODO: Replace sleep with another type of wait. Cart widget is enabled to be clicked on but after click cart pop up does not open.
        // After some time click on this widget actually triggers opening a cart pop up. I haven't found the condition
        // when click on cart widget opens a cart pop up.
        Thread.sleep(2000);
        click(CART_BUTTON);
        return at(CartPopUp.class);
    }

    @Step("Cart widget should have icon that identifies {expectedCartSize} elements in cart")
    public <T extends BasePage> T shouldHaveCartSize(int expectedCartSize, final Class<T> pageClass) {
        checkCartSize(getTotalCartSize(), expectedCartSize);
        return at(pageClass);
    }

    private int getTotalCartSize() {
        return Integer.valueOf(find(CART_BUTTON_DATA, WaitCondition.visible).getText());
    }

    @Override
    public CartWidget withCartWidget() {
        throw new UnsupportedOperationException("Cart widget is not available from Cart widget!");
    }
}
