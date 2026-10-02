package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class BurgerIntegrationTest {

    @Test
    public void burgerWithRealObjectsShouldWorkCorrectly() {
        Database database = new Database();
        Burger burger = new Burger();

        burger.setBuns(database.availableBuns().get(0)); // black bun, 100
        burger.addIngredient(database.availableIngredients().get(1)); // sour cream, 200
        burger.addIngredient(database.availableIngredients().get(4)); // dinosaur, 200
        burger.addIngredient(database.availableIngredients().get(3)); // cutlet, 100
        burger.addIngredient(database.availableIngredients().get(5)); // sausage, 300

        burger.moveIngredient(2, 1); // cutlet -> index 1
        burger.removeIngredient(3);  // удаляем sausage (300)

        assertEquals(700f, burger.getPrice(), 0.0001f);

        String receipt = burger.getReceipt();
        assertTrue(receipt.contains("(==== black bun ====)"));
        assertTrue(receipt.contains("= sauce sour cream ="));
        assertTrue(receipt.contains("= filling cutlet ="));
        assertTrue(receipt.contains("= filling dinosaur ="));
        assertFalse(receipt.contains("sausage"));
        assertTrue(receipt.contains("Price: 700"));
    }

    @Test
    public void emptyBurgerWithOnlyBunShouldHaveCorrectPrice() {
        Burger burger = new Burger();
        Bun bun = new Bun("white bun", 200);
        burger.setBuns(bun);

        assertEquals(400f, burger.getPrice(), 0.0001f);
    }
}