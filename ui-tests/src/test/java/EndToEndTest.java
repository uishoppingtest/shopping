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
                .selectProduct("Glasses")
                .addToCart().shouldLocateOnTheRight().shouldHaveItemsInCart("Glasses", 1)
                .removeItem("Glasses").shouldHaveEmptyCart()
                .closeCart(ProductPage.class)
                .addToCart().shouldHaveItemsInCart("Glasses",1)
                .closeCart(ProductPage.class).withNavigationBar().shouldHaveCartSize(1, ProductPage.class)
                .withNavigationBar().navigateToShop()
                .withNavigationBar().navigateToCart().shouldHaveItemsInCart("Glasses",1)
                .closeCart(ShopPage.class)
                .selectProduct("product")
                .addToCart().shouldHaveItemsInCart("product",1)
                .viewCart()
                .setProductQuantity("Glasses", 3)
                .removeProduct("product");
    }
}
