package praktikum;

import org.junit.Test;

import static org.junit.Assert.*;

public class IngredientTest {

    @Test
    public void constructorShouldSetType() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals(IngredientType.SAUCE, ingredient.type);
    }

    @Test
    public void constructorShouldSetName() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals("hot sauce", ingredient.name);
    }

    @Test
    public void constructorShouldSetPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        assertEquals(100f, ingredient.price, 0.0001f);
    }

    @Test
    public void getTypeShouldReturnSauceType() {
        Ingredient sauce = new Ingredient(IngredientType.SAUCE, "sauce", 50f);
        assertEquals(IngredientType.SAUCE, sauce.getType());
    }

    @Test
    public void getTypeShouldReturnFillingType() {
        Ingredient filling = new Ingredient(IngredientType.FILLING, "filling", 75f);
        assertEquals(IngredientType.FILLING, filling.getType());
    }

    @Test
    public void getNameShouldReturnName() {
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 100f);
        assertEquals("cutlet", ingredient.getName());
    }

    @Test
    public void getPriceShouldReturnPrice() {
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "chili", 300f);
        assertEquals(300f, ingredient.getPrice(), 0.0001f);
    }
}
