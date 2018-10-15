package com.shop.pages;

import com.shop.utils.WaitCondition;
import org.openqa.selenium.By;

import static com.shop.pages.PageFactory.at;

public class ShopPage extends BasePage {

    private static final String PRODUCT_SELECTOR = "//h3[contains(text(),'%s')]";
    private static final By SHOP_FRAME = By.xpath("//iframe[@title='Shop']");

    public ShopPage() {
        super();
        switchToFrame(SHOP_FRAME, WaitCondition.visible);
    }

    @Override
    public NavigationBar withNavigationBar() {
        return at(NavigationBar.class);
    }

    public ProductPage selectProduct(String productName) throws InterruptedException {
        Thread.sleep(1000);
        click(By.xpath(String.format(PRODUCT_SELECTOR, productName)));
        return at(ProductPage.class);
    }

}
