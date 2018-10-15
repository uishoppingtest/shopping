package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;

import static com.shop.asserts.CartAsserts.checkCartSize;
import static com.shop.asserts.GeneralPageAssers.checkText;
import static com.shop.asserts.GeneralPageAssers.isPageOpen;
import static com.shop.asserts.PositionAsserts.checkElementIsOnTheRightSide;
import static com.shop.pages.PageFactory.at;

public class CartPopUp extends BasePage {

    private static final By CART_FRAME = By.xpath("//iframe[contains(@class,'Popup')]");
    private static final By MINI_CART = By.xpath("//div[@class='minicart active']");
    private static final By EMPTY_CART = By.xpath("//section[@class='cart-content']//span");
    private static final By CLOSE_CART = By.xpath("//button[@title='Close cart widget']");
    private static final By VIEW_CART = By.xpath("//footer//a[@data-hook='widget-view-cart-button']");
    private static final By CART_BACKDROP = By.xpath("//div[@data-hook='cart-widget-backdrop']");
    private static final String ITEM_NAME_IN_CARD_PATH = "//div[@data-hook='cart-widget-name' and contains(text(),'%s')]";
    private static final String REMOVE_ITEM_PATH = ITEM_NAME_IN_CARD_PATH +
            "/ancestor::div[@class='item-info']/../button[@class='remove-item']";
    private static final String ITEM_QUANTITY_IN_CART = ITEM_NAME_IN_CARD_PATH +
            "/ancestor::div[@class='item-info']//div[@class='item-quantity']/span";

    public CartPopUp() {
        super();
        switchToFrame(CART_FRAME, WaitCondition.enabled);
        isOpen();
    }

    @Override
    public void isOpen() {
        isPageOpen(find(CART_BACKDROP));
    }


    public CartPopUp removeItem(String itemName) {
        By itemToBeRemoved = byXPath(String.format(ITEM_NAME_IN_CARD_PATH, itemName));
        By removeItemButton = byXPath(String.format(REMOVE_ITEM_PATH, itemName));
        moveToElement(itemToBeRemoved);
        click(removeItemButton, WaitCondition.enabled);
        return this;
    }

    public <T extends Page> T closeCart(final Class<T> expectedPageClass) throws InterruptedException {
        //TODO: fix this shit. Wait for animation (open cart) to be finished
        Thread.sleep(1000);
        click(CLOSE_CART, WaitCondition.visible);
        waitForInvisibility(CART_BACKDROP);
        return at(expectedPageClass);
    }

    public CartPopUp shouldLocateOnTheRight() {
        checkElementIsOnTheRightSide(find(MINI_CART, WaitCondition.visible), Page.driver);
        return this;
    }

    public CartPopUp shouldHaveItemsInCart(String productName, int expectedSize) {
        checkCartSize(getAmountOfProductsInCart(productName), expectedSize);
        return this;
    }

    public CartPopUp shouldHaveEmptyCart() {
        checkText(find(EMPTY_CART),"Cart is empty");
        return this;
    }

    public CartPage viewCart() {
        click(VIEW_CART, WaitCondition.enabled);
        return at(CartPage.class);
    }

    private int getAmountOfProductsInCart(String productName) {
        return Integer.valueOf(find(byXPath(String.format(ITEM_QUANTITY_IN_CART, productName)),
                WaitCondition.visible).getText());
    }

    @Override
    public NavigationBar withNavigationBar() {
        throw new UnsupportedOperationException("Navigation bar is not available from Cart pop-up!");
    }

    @Override
    public CartWidget withCartWidget() {
        throw new UnsupportedOperationException("Cart widget is not available from Cart pop-up!");
    }
}
