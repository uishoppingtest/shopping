import com.shop.pages.HomePage;
import com.shop.pages.ProductPage;
import com.shop.pages.StorePage;
import org.junit.jupiter.api.Test;

import static com.shop.pages.PageFactory.open;


public class EndToEndTest {

    @Test
    public void test() throws InterruptedException {
        open(HomePage.class)
                .navigateToStore()
                .selectProduct("Glasses").shouldBeProduct("Glasses")
                .addToCart().shouldLocateOnTheRight().shouldHaveProductsInCart("Glasses", 1)
                .removeItem("Glasses").shouldHaveEmptyCart()
                .closeCart(ProductPage.class)
                .addToCart().shouldHaveProductsInCart("Glasses",1)
                .closeCart(ProductPage.class).withCartWidget().shouldHaveCartSize(1, ProductPage.class)
                .navigateToStore()
                .withCartWidget().navigateToCart().shouldHaveProductsInCart("Glasses",1)
                .closeCart(StorePage.class)
                .selectProduct("product").shouldBeProduct("product")
                .addToCart().shouldHaveProductsInCart("product",1)
                .viewCart().shouldHaveInCart("Glasses", 1).shouldHaveInCart("product", 1)
                .setProductQuantity("Glasses", 3).shouldHaveInCart("Glasses", 3)
                .removeProduct("product").shouldHaveInCart("product", 0);
    }
}
