
import org.junit.jupiter.api.*;
import shop.VirtualItem;

public class VirtualItemTest {
    VirtualItem virtualItem;

    @BeforeEach
    void init() {
        virtualItem = new VirtualItem();
    }

    @Test
    @DisplayName("The size on the disk should be as the input.")
    void sizeDiskAsInput() {
        final int sizeOnDisk = 20000;
        virtualItem.setSizeOnDisk(sizeOnDisk);
        Assertions.assertEquals(sizeOnDisk,virtualItem.getSizeOnDisk());
    }
}
