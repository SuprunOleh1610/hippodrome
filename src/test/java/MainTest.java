import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    @Test
    @Timeout(value = 22)
    public void timout() throws Exception {
        Main.main(null);
    }
}