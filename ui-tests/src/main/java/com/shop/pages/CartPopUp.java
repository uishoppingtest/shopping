package com.shop.pages;

import com.shop.utils.WaitCondition;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import static com.shop.asserts.CartAsserts.checkCartSize;
import static com.shop.asserts.GeneralPageAsserts.checkText;
import static com.shop.asserts.PositionAsserts.checkElementIsOnTheRightSide;
import static com.shop.pages.PageFactory.at;

public class CartPopUp extends BasePage {

    private static final By CART_FRAME = By.xpath("//iframe[contains(@class,'Popup')]");
    private static final By OPEN_MINI_CART = By.xpath("//div[@class='minicart active']");
    private static final By CART_POPUP = By.xpath("//cart-popup");

    private static final By EMPTY_CART_CONTENT = By.xpath("//section[@class='cart-content']//span");
    private static final By CLOSE_CART_BUTTON = By.xpath("//button[@title='Close cart widget']");
    private static final By VIEW_CART_BUTTON = By.xpath("//footer//a[@data-hook='widget-view-cart-button']");
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
        find(OPEN_MINI_CART, WaitCondition.enabled);
    }

    @Step("Remove product {productName} from cart")
    public CartPopUp removeItem(String productName) {
        By itemToBeRemoved = By.xpath(String.format(ITEM_NAME_IN_CARD_PATH, productName));
        By removeItemButton = By.xpath(String.format(REMOVE_ITEM_PATH, productName));
        moveToElement(itemToBeRemoved);
        click(removeItemButton, WaitCondition.enabled);
        return this;
    }

    @Step("Close cart pop up")
    public <T extends Page> T closeCart(final Class<T> expectedPageClass) {
        click(CLOSE_CART_BUTTON);
        waitForInvisibility(CART_POPUP);
        return at(expectedPageClass);
    }

    @Step("Cart pop up should be located on the right side of the screen")
    public CartPopUp shouldLocateOnTheRight() {
        checkElementIsOnTheRightSide(find(OPEN_MINI_CART, WaitCondition.visible), Page.driver);
        return this;
    }

    @Step("Cart should have {expectedSize} products {productName} in cart")
    public CartPopUp shouldHaveProductsInCart(String productName, int expectedSize) {
        checkCartSize(getAmountOfProductsInCart(productName), expectedSize);
        return this;
    }

    @Step("Should be empty cart")
    public CartPopUp shouldHaveEmptyCart() {
        checkText(find(EMPTY_CART_CONTENT),"Cart is empty");
        return this;
    }

    @Step("Navigate to view cart page")
    public CartPage viewCart() {
        click(VIEW_CART_BUTTON);
        return at(CartPage.class);
    }

    private int getAmountOfProductsInCart(String productName) {
        return Integer.valueOf(find(By.xpath(String.format(ITEM_QUANTITY_IN_CART, productName)),
                WaitCondition.visible).getText());
    }

    @Override
    public CartWidget withCartWidget() {
        throw new UnsupportedOperationException("Cart widget is not available from Cart pop-up!");
    }
}
