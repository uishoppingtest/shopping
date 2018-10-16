package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;

import static com.shop.pages.PageFactory.at;

public class StorePage extends BasePage {

    private static final String PRODUCT_SELECTOR = "//h3[contains(text(),'%s')]";
    private static final By SHOP_FRAME = By.xpath("//iframe[@title='Shop']");
    private static final By SHOP_CONTAINER = By.xpath("//section[@aria-label='Product Gallery']");

    public StorePage() {
        super();
        switchToFrame(SHOP_FRAME, WaitCondition.enabled);
        isOpen();
    }

    @Override
    public void isOpen() {
        find(SHOP_CONTAINER);
    }

    public ProductPage selectProduct(String productName) {
        click(By.xpath(String.format(PRODUCT_SELECTOR, productName)));
        return at(ProductPage.class);
    }
}
