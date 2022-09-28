
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import parser.JsonParser;
import parser.NoSuchFileException;
import parser.Parser;
import shop.Cart;
import shop.RealItem;
import shop.VirtualItem;
import java.io.*;

public class JsonParserTest {
    private Parser parser;
    private Cart andrewCart;
    RealItem car;
    VirtualItem disk;
    Gson gson;

    @BeforeEach
    void init() {
        parser = new JsonParser();
        andrewCart = new Cart("andrew-cart");
        gson = new Gson();
        disk = new VirtualItem();
        car = new RealItem();
    }

    @Test
    @DisplayName("Should be proper data in Json on the cart.")
    void properDataJsonOnTheCart() {
        try {
            car.setName("Audi");
            car.setPrice(32026.9);
            car.setWeight(1560);

            disk = new VirtualItem();
            disk.setName("Windows");
            disk.setPrice(11);
            disk.setSizeOnDisk(20000);

            andrewCart.addRealItem(car);
            andrewCart.addVirtualItem(disk);
            parser.writeToFile(andrewCart);

            BufferedReader reader = new BufferedReader(new FileReader("src/main/resources/andrew-cart.json"));
            Cart fromJson = gson.fromJson(reader.readLine(), Cart.class);

            Assertions.assertNotNull(fromJson);
            Assertions.assertAll(
                    ()-> Assertions.assertEquals(andrewCart.getCartName(), fromJson.getCartName()),
                    () -> Assertions.assertEquals(andrewCart.getTotalPrice(), fromJson.getTotalPrice()));

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @EnabledOnOs(value = {OS.WINDOWS, OS.MAC})
    @Disabled()
    @DisplayName("The cart reads the proper Json file.")
    void readProperFile() {
        parser = new JsonParser();
        Cart eugenCart = parser.readFromFile(new File("src/main/resources/eugen-cart.json"));
        Assertions.assertEquals("eugen-cart", eugenCart.getCartName());
    }

    @DisplayName("NoSuchFileException should be thrown when the path name is wrong.")
    @ParameterizedTest
    @ValueSource(strings = {"src/main/andrew-cart.json","src/resources/andrew-cart.json",
            "main/resources/andrew-cart.json","src/main/resources",
            "src/main/resources/andre-cart.json"})
    void throwNoSuchFileException(String pathname) {
        Assertions.assertThrows(NoSuchFileException.class,
                () -> parser.readFromFile(new File(pathname)));
    }

}




