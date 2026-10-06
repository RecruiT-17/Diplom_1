package praktikum;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BurgerIntegrationTest {

    private Burger burger;
    private Database database;

    private Bun blackBun;
    private Ingredient sourCream;
    private Ingredient dinosaur;
    private Ingredient cutlet;
    private Ingredient sausage;

    @Before
    public void setUp() {
        burger = new Burger();

        blackBun = mock(Bun.class);
        when(blackBun.getName()).thenReturn("black bun");
        when(blackBun.getPrice()).thenReturn(100f);

        sourCream = mock(Ingredient.class);
        when(sourCream.getName()).thenReturn("sour cream");
        when(sourCream.getPrice()).thenReturn(200f);
        when(sourCream.getType()).thenReturn(IngredientType.SAUCE);

        dinosaur = mock(Ingredient.class);
        when(dinosaur.getName()).thenReturn("dinosaur");
        when(dinosaur.getPrice()).thenReturn(200f);
        when(dinosaur.getType()).thenReturn(IngredientType.FILLING);

        cutlet = mock(Ingredient.class);
        when(cutlet.getName()).thenReturn("cutlet");
        when(cutlet.getPrice()).thenReturn(100f);
        when(cutlet.getType()).thenReturn(IngredientType.FILLING);

        sausage = mock(Ingredient.class);
        when(sausage.getName()).thenReturn("sausage");
        when(sausage.getPrice()).thenReturn(300f);
        when(sausage.getType()).thenReturn(IngredientType.FILLING);

        database = mock(Database.class);
        when(database.availableBuns()).thenReturn(Arrays.asList(blackBun));
        when(database.availableIngredients())
                .thenReturn(Arrays.asList(sourCream, dinosaur, cutlet, sausage));

        List<Bun> buns = database.availableBuns();
        List<Ingredient> ingredients = database.availableIngredients();

        burger.setBuns(buns.get(0));
        burger.addIngredient(ingredients.get(0));
        burger.addIngredient(ingredients.get(1));
        burger.addIngredient(ingredients.get(2));
        burger.addIngredient(ingredients.get(3));

        burger.moveIngredient(2, 1);
        burger.removeIngredient(3);
    }


    @Test
    public void burgerShouldHaveCorrectPrice() {
         assertEquals(700f, burger.getPrice(), 0.0001f);
    }

    @Test
    public void receiptShouldContainBunName() {
        assertTrue(burger.getReceipt().contains("(==== black bun ====)"));
    }

    @Test
    public void receiptShouldContainSourCream() {
        assertTrue(burger.getReceipt().contains("= sauce sour cream ="));
    }

    @Test
    public void receiptShouldContainCutlet() {
        assertTrue(burger.getReceipt().contains("= filling cutlet ="));
    }

    @Test
    public void receiptShouldContainDinosaur() {
        assertTrue(burger.getReceipt().contains("= filling dinosaur ="));
    }

    @Test
    public void receiptShouldNotContainRemovedSausage() {
        assertFalse(burger.getReceipt().contains("sausage"));
    }

    @Test
    public void receiptShouldContainCorrectPrice() {
        assertTrue(burger.getReceipt().contains("Price: 700"));
    }

    @Test
    public void emptyBurgerWithOnlyBunShouldHaveCorrectPrice() {
        Bun whiteBun = mock(Bun.class);
        when(whiteBun.getPrice()).thenReturn(200f);

        Burger emptyBurger = new Burger();
        emptyBurger.setBuns(whiteBun);

        assertEquals(400f, emptyBurger.getPrice(), 0.0001f);
    }
}