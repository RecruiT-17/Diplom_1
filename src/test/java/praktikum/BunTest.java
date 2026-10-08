package praktikum;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BunTest {

    @Test
    public void constructorShouldSetName() {
        Bun bun = new Bun("test bun", 150f);
        assertEquals("test bun", bun.name);
    }

    @Test
    public void constructorShouldSetPrice() {
        Bun bun = new Bun("test bun", 150f);
        assertEquals(150f, bun.price, 0.0001f);
    }

    @Test
    public void getNameShouldReturnName() {
        Bun bun = new Bun("sesame bun", 250f);
        assertEquals("sesame bun", bun.getName());
    }

    @Test
    public void getPriceShouldReturnPrice() {
        Bun bun = new Bun("rye bun", 300f);
        assertEquals(300f, bun.getPrice(), 0.0001f);
    }

    @Test
    public void bunWithZeroPriceShouldWork() {
        Bun bun = new Bun("free bun", 0f);
        assertEquals(0f, bun.getPrice(), 0.0001f);
    }
}
