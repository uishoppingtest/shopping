package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;

import java.util.Optional;

import static com.shop.asserts.CartAsserts.checkCartSize;
import static com.shop.pages.PageFactory.at;

public class NavigationBar extends BasePage {

    private final By STORES_LINK = By.xpath("//a[@role='button']//p[text()='STORES']");
    private static final By CART_FRAME = By.xpath("//iframe[@title='Wix Stores']");
    private final By CART_BUTTON = By.xpath("//a[@id='cart-widget-button']");
    private final By CART_BUTTON_DATA = By.xpath("//a[@id='cart-widget-button']//*[name()='text']");

    public NavigationBar() {
        super();
    }

    @Override
    public NavigationBar withNavigationBar() {
        return null;
    }

    public ShopPage navigateToShop() throws InterruptedException {
        Thread.sleep(1000);
        click(STORES_LINK, WaitCondition.enabled);
        return at(ShopPage.class);
    }

    public CartPopUp navigateToCart() throws InterruptedException {
        switchToFrame(CART_FRAME, WaitCondition.visible);
        Thread.sleep(2000);
        click(CART_BUTTON, WaitCondition.enabled);
        return at(CartPopUp.class);
    }

    public <T extends BasePage> T shouldHaveCartSize(int expectedCartSize, final Class<T> pageClass) {
        switchToFrame(CART_FRAME, WaitCondition.visible);
        checkCartSize(getTotalCartSize(), expectedCartSize);
        return at(pageClass);
    }

    public int getTotalCartSize() {
      return Integer.valueOf(find(CART_BUTTON_DATA, WaitCondition.visible).getText());
    }
}
