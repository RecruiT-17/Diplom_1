package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;

    @Mock private Bun bun;
    @Mock private Ingredient sauce;
    @Mock private Ingredient filling;

    private final float bunPrice;
    private final float saucePrice;
    private final float fillingPrice;
    private final float expectedPrice;

    public BurgerTest(float bunPrice, float saucePrice, float fillingPrice, float expectedPrice) {
        this.bunPrice = bunPrice;
        this.saucePrice = saucePrice;
        this.fillingPrice = fillingPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "bun={0}, sauce={1}, filling={2} → price={3}")
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {100f, 50f, 200f, 450f},
                {0f, 0f, 0f, 0f},
                {150.5f, 10.25f, 20.5f, 331.75f},
                {300f, 300f, 300f, 1200f}
        });
    }

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        burger = new Burger();
    }


    @Test
    public void setBunsShouldAssignBunToBurger() {
        burger.setBuns(bun);
        assertEquals(bun, burger.bun);
    }


    @Test
    public void addIngredientShouldIncreaseListSize() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        assertEquals(2, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldStoreFirstIngredient() {
        burger.addIngredient(sauce);
        assertSame(sauce, burger.ingredients.get(0));
    }

    @Test
    public void addIngredientShouldStoreSecondIngredient() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        assertSame(filling, burger.ingredients.get(1));
    }

    @Test
    public void addIngredientShouldAllowDuplicates() {
        burger.addIngredient(sauce);
        burger.addIngredient(sauce);
        assertEquals(2, burger.ingredients.size());
    }


    @Test
    public void removeIngredientShouldDecreaseListSize() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientShouldShiftRemainingElements() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(0);
        assertSame(filling, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveLastElement() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.removeIngredient(1);
        assertSame(sauce, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientFromEmptyListShouldThrowException() {
        assertThrows(IndexOutOfBoundsException.class, () -> burger.removeIngredient(0));
    }


    @Test
    public void moveIngredientForwardShouldPutFillingFirst() {
        Ingredient extra = mock(Ingredient.class);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.addIngredient(extra);

        burger.moveIngredient(0, 2);

        assertSame(filling, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientForwardShouldPutExtraSecond() {
        Ingredient extra = mock(Ingredient.class);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.addIngredient(extra);

        burger.moveIngredient(0, 2);

        assertSame(extra, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientForwardShouldPutSauceLast() {
        Ingredient extra = mock(Ingredient.class);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.addIngredient(extra);

        burger.moveIngredient(0, 2);

        assertSame(sauce, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientBackwardShouldPutExtraFirst() {
        Ingredient extra = mock(Ingredient.class);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.addIngredient(extra);

        burger.moveIngredient(2, 0);

        assertSame(extra, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientBackwardShouldPutSauceSecond() {
        Ingredient extra = mock(Ingredient.class);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.addIngredient(extra);

        burger.moveIngredient(2, 0);

        assertSame(sauce, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientBackwardShouldPutFillingLast() {
        Ingredient extra = mock(Ingredient.class);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);
        burger.addIngredient(extra);

        burger.moveIngredient(2, 0);

        assertSame(filling, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredientToSameIndexShouldKeepFirstElement() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(1, 1);

        assertSame(sauce, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientToSameIndexShouldKeepSecondElement() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(1, 1);

        assertSame(filling, burger.ingredients.get(1));
    }

    @Test
    public void moveIngredientFromInvalidIndexShouldThrowException() {
        burger.addIngredient(sauce);
        assertThrows(IndexOutOfBoundsException.class, () -> burger.moveIngredient(5, 0));
    }


    @Test
    public void getPriceShouldCalculateCorrectly() {
        when(bun.getPrice()).thenReturn(bunPrice);
        when(sauce.getPrice()).thenReturn(saucePrice);
        when(filling.getPrice()).thenReturn(fillingPrice);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        assertEquals(expectedPrice, burger.getPrice(), 0.0001f);
    }

    @Test
    public void getPriceWithOnlyBunShouldBeDoubleBunPrice() {
        when(bun.getPrice()).thenReturn(bunPrice);
        burger.setBuns(bun);
        assertEquals(bunPrice * 2, burger.getPrice(), 0.0001f);
    }

    @Test
    public void getPriceWithoutBunShouldThrowException() {
        assertThrows(NullPointerException.class, () -> burger.getPrice());
    }


    @Test
    public void getReceiptShouldStartWithBunLine() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        assertTrue(burger.getReceipt().startsWith("(==== black bun ====)"));
    }

    @Test
    public void getReceiptShouldContainBunNameTwice() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        String receipt = burger.getReceipt();
        int first = receipt.indexOf("(==== black bun ====)");
        int last = receipt.lastIndexOf("(==== black bun ====)");
        assertNotEquals(first, last);
    }

    @Test
    public void getReceiptShouldContainSauceLine() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200f);
        when(sauce.getName()).thenReturn("hot sauce");
        when(sauce.getPrice()).thenReturn(100f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        assertTrue(burger.getReceipt().contains("= sauce hot sauce ="));
    }

    @Test
    public void getReceiptShouldContainFillingLine() {
        when(bun.getName()).thenReturn("white bun");
        when(bun.getPrice()).thenReturn(200f);
        when(filling.getName()).thenReturn("cutlet");
        when(filling.getPrice()).thenReturn(100f);
        when(filling.getType()).thenReturn(IngredientType.FILLING);

        burger.setBuns(bun);
        burger.addIngredient(filling);

        assertTrue(burger.getReceipt().contains("= filling cutlet ="));
    }

    @Test
    public void getReceiptShouldContainPriceLine() {
        when(bun.getName()).thenReturn("red bun");
        when(bun.getPrice()).thenReturn(300f);
        when(sauce.getName()).thenReturn("chili sauce");
        when(sauce.getPrice()).thenReturn(100f);
        when(sauce.getType()).thenReturn(IngredientType.SAUCE);

        burger.setBuns(bun);
        burger.addIngredient(sauce);

        assertTrue(burger.getReceipt().contains("Price: 700"));
    }

    @Test
    public void getReceiptWithOnlyBunShouldNotContainSauce() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        assertFalse(burger.getReceipt().contains("= sauce"));
    }

    @Test
    public void getReceiptWithOnlyBunShouldNotContainFilling() {
        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);
        burger.setBuns(bun);

        assertFalse(burger.getReceipt().contains("= filling"));
    }

    @Test
    public void getReceiptWithoutBunShouldThrowException() {
        assertThrows(NullPointerException.class, () -> burger.getReceipt());
    }
}