package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;

import static com.shop.asserts.CartAsserts.checkCartSize;
import static com.shop.pages.PageFactory.at;

public class CartWidget extends BasePage{

    private final By CART_FRAME = By.xpath("//iframe[@title='Wix Stores']");
    private final By CART_WIDGET = By.xpath("//cart-widget");
    private final By CART_BUTTON = By.xpath("//a[@id='cart-widget-button']");
    private final By CART_BUTTON_DATA = By.xpath("//a[@id='cart-widget-button']//*[name()='text']");

    public CartWidget() {
        super();
        switchToFrame(CART_FRAME, WaitCondition.visible);
        isOpen();
    }

    @Override
    public void isOpen() {
        find(CART_WIDGET);
    }

    public CartPopUp navigateToCart()  {
        //TODO: Wait for Store page load to be finished. sleep(1000) is not enough.
        //Thread.sleep(2000);
        click(CART_BUTTON);
        return at(CartPopUp.class);
    }

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
