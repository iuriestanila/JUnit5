import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;

public class CartTest {
    Cart andrewCart;
    RealItem car;
    VirtualItem disk;

    @BeforeEach
    void init() {
        andrewCart = new Cart("andrew-cart");
        car = new RealItem();
        disk = new VirtualItem();
    }

    @Test
    @DisplayName("Should be added an real item as the input.")
    void addRealItemAsInput() {
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        andrewCart.addRealItem(car);
        Assertions.assertNotNull(andrewCart);
        Assertions.assertEquals("andrew-cart", andrewCart.getCartName());
    }

    @Test
    @DisplayName("Should be calculated the total price of an added RealItem.")
    void totalPriceRealItem() {
        car.setName("Audi");
        car.setPrice(32026.9);
        car.setWeight(1560);

        andrewCart.addRealItem(car);
        final double expected = 38432.28;
        Assertions.assertEquals(expected, andrewCart.getTotalPrice());
    }


    @Test
    @DisplayName("Should be added an virtual item as the input.")
    void addVirtualItemAsInput() {
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);

        Assertions.assertAll(()-> Assertions.assertEquals("Windows", disk.getName()),
                 () -> Assertions.assertEquals(11, disk.getPrice()),
                 () -> Assertions.assertEquals(20000, disk.getSizeOnDisk()));
    }

    @Test
    @DisplayName("Should be calculated the total price of an added VirtualItem.")
    void totalPriceVirtualItem() {
        disk.setName("Windows");
        disk.setPrice(11);
        disk.setSizeOnDisk(20000);

        andrewCart.addVirtualItem(disk);
        double expected = 13.2;
        Assertions.assertEquals(expected, andrewCart.getTotalPrice());
    }
}
