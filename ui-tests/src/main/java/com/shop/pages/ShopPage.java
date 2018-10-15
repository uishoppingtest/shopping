package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;

import static com.shop.asserts.GeneralPageAssers.isPageOpen;
import static com.shop.pages.PageFactory.at;

public class ShopPage extends BasePage {

    private static final String PRODUCT_SELECTOR = "//h3[contains(text(),'%s')]";
    private static final By SHOP_FRAME = By.xpath("//iframe[@title='Shop']");
    private static final By SHOP_CONTAINER = By.xpath("//section[@aria-label='Product Gallery']");

    public ShopPage() {
        super();
        switchToFrame(SHOP_FRAME, WaitCondition.visible);
        isOpen();
    }

    @Override
    public void isOpen() {
        isPageOpen(find(SHOP_CONTAINER));
    }

    public ProductPage selectProduct(String productName) throws InterruptedException {
        //TODO: fix this shit. Wait until animation (close cart) is finished
        Thread.sleep(1000);
        click(By.xpath(String.format(PRODUCT_SELECTOR, productName)));
        return at(ProductPage.class);
    }
}
