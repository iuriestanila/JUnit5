import org.junit.jupiter.api.*;
import shop.RealItem;

public class RealItemTest {
    RealItem car;

    @BeforeEach
    void init() {
        car = new RealItem();
    }

    @DisplayName("The weight of the object should be as the input.")
    @RepeatedTest(value = 4,name = "repetitions of this test {currentRepetition} of {totalRepetitions}.")
    void weighObjectAsInput() {
        final double weight = 32026.9;
        car.setWeight(weight);
        Assertions.assertEquals(weight,car.getWeight());
    }
}
