import com.shop.pages.HomePage;
import com.shop.pages.ProductPage;
import com.shop.pages.ShopPage;
import org.junit.jupiter.api.Test;

import static com.shop.pages.PageFactory.open;


public class EndToEndTest {

    @Test
    public void test() throws InterruptedException {
        open(HomePage.class)
                .navigateToShop()
                .selectProduct("Glasses").shouldBeProduct("Glasses")
                .addToCart().shouldLocateOnTheRight().shouldHaveItemsInCart("Glasses", 1)
                .removeItem("Glasses").shouldHaveEmptyCart()
                .closeCart(ProductPage.class)
                .addToCart().shouldHaveItemsInCart("Glasses",1)
                .closeCart(ProductPage.class).withCartWidget().shouldHaveCartSize(1, ProductPage.class)
                .withNavigationBar().navigateToShop()
                .withCartWidget().navigateToCart().shouldHaveItemsInCart("Glasses",1)
                .closeCart(ShopPage.class)
                .selectProduct("product").shouldBeProduct("product")
                .addToCart().shouldHaveItemsInCart("product",1)
                .viewCart() //TODO: add verification here
                .setProductQuantity("Glasses", 3)
                .removeProduct("product");
    }
}
