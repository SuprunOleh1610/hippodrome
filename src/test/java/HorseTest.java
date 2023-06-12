import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class HorseTest {

    @Test
    public void nameNullException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
    }

    @Test
    public void nameNullMessage() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1, 1));
        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "\t\t", "\n\n\n"})
    public void nameBlankException(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", "    ", "\t\t", "\n\n\n"})
    public void nameBlankMessage(String name) {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1, 1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void speedNegativeException() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("test name", -1, 1));
    }

    @Test
    public void speedNegativeMessage() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new Horse("test name", -1, 1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void distanceNegativeMessage() {
        Throwable e = assertThrows(IllegalArgumentException.class, () -> new Horse("test name", 1, -1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void getName() {
        Horse horse = new Horse("test name", 1, 1);
        assertEquals("test name", horse.getName());
    }

    @Test
    public void getSpeed() {
        Horse horse = new Horse("test name", 98464.34, 1);
        assertEquals(98464.34, horse.getSpeed());
    }

    @Test
    public void getDistance() {
        Horse horse = new Horse("test name", 1, 3164.45);
        assertEquals(3164.45, horse.getDistance());
    }

    @Test
    public void getDistanceZero() {
        Horse horse = new Horse("test name", 1);
        assertEquals(0, horse.getDistance());
    }

    @Test
    public void moveGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            new Horse("test name", 1, 1).move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.1, 0.5, 0.9, 1.0, 2, 3, 4.5})
    void moveDistance(double random) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("test name", 2, 3);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(3 + 2 * random, horse.getDistance());
        }
    }
}