package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() {
        database = new Database();
    }


    @Test
    public void availableBunsShouldReturnThreeBuns() {
        assertEquals(3, database.availableBuns().size());
    }

    @Test
    public void firstBunShouldBeBlackBun() {
        assertEquals("black bun", database.availableBuns().get(0).getName());
    }

    @Test
    public void firstBunShouldCost100() {
        assertEquals(100f, database.availableBuns().get(0).getPrice(), 0.0001f);
    }

    @Test
    public void secondBunShouldBeWhiteBun() {
        assertEquals("white bun", database.availableBuns().get(1).getName());
    }

    @Test
    public void secondBunShouldCost200() {
        assertEquals(200f, database.availableBuns().get(1).getPrice(), 0.0001f);
    }

    @Test
    public void thirdBunShouldBeRedBun() {
        assertEquals("red bun", database.availableBuns().get(2).getName());
    }

    @Test
    public void thirdBunShouldCost300() {
        assertEquals(300f, database.availableBuns().get(2).getPrice(), 0.0001f);
    }


    @Test
    public void availableIngredientsShouldReturnSixItems() {
        assertEquals(6, database.availableIngredients().size());
    }

    @Test
    public void firstIngredientShouldBeHotSauce() {
        assertEquals("hot sauce", database.availableIngredients().get(0).getName());
    }

    @Test
    public void firstIngredientShouldHaveSauceType() {
        assertEquals(IngredientType.SAUCE, database.availableIngredients().get(0).getType());
    }

    @Test
    public void secondIngredientShouldBeSourCream() {
        assertEquals("sour cream", database.availableIngredients().get(1).getName());
    }

    @Test
    public void thirdIngredientShouldBeChiliSauce() {
        assertEquals("chili sauce", database.availableIngredients().get(2).getName());
    }

    @Test
    public void fourthIngredientShouldBeCutlet() {
        assertEquals("cutlet", database.availableIngredients().get(3).getName());
    }

    @Test
    public void fourthIngredientShouldHaveFillingType() {
        assertEquals(IngredientType.FILLING, database.availableIngredients().get(3).getType());
    }

    @Test
    public void fifthIngredientShouldBeDinosaur() {
        assertEquals("dinosaur", database.availableIngredients().get(4).getName());
    }

    @Test
    public void sixthIngredientShouldBeSausage() {
        assertEquals("sausage", database.availableIngredients().get(5).getName());
    }
}
