package com.neopragma.billing;

import java.util.UUID;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertThat;
import org.junit.Test;

/**
 * Test of LineItem class.
 */
public class LineItemTest {

  private final double TEST_POSTITIVE_AMOUNT = 40d * Math.random() + 1d;
  private final double TEST_NEGATIVE_AMOUNT = -22d * Math.random() - 1d;
  private final int TEST_POSITIVE_QUANTITY = (int) (12d * Math.random()) + 1;
  private final int TEST_NEGATIVE_QUANTITY = (int) (-17d * Math.random()) - 1;
  private final String TEST_SKU = UUID.randomUUID().toString();
  private final LineItem INSTANCE = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, TEST_POSTITIVE_AMOUNT);

  /**
   * Test of getAmount method, of class LineItem.
   */
  @Test
  public void testGetAmount() {
    assertThat("Wrong amount.", INSTANCE.getAmount(), closeTo(TEST_POSTITIVE_AMOUNT, 0.001));
  }

  /**
   * Test of getQuantity method, of class LineItem.
   */
  @Test
  public void testGetQuantity() {
    assertThat("Wrong amount.", INSTANCE.getQuantity(), closeTo(TEST_POSITIVE_QUANTITY, 0.001));
  }

  /**
   * Test of getSKU method, of class LineItem.
   */
  @Test
  public void testGetSKU() {
    assertThat("Wrong amount.", INSTANCE.getSKU(), is(TEST_SKU));
  }

  @Test(expected = RuntimeException.class)
  public void testConstructorZeroQuantity() {
    newLineItem(TEST_SKU, 0, TEST_POSTITIVE_AMOUNT);
  }

  @Test(expected = RuntimeException.class)
  public void testConstructorNegativeQuantity() {
    newLineItem(TEST_SKU, TEST_NEGATIVE_QUANTITY, TEST_POSTITIVE_AMOUNT);
  }

  @Test
  public void testConstructorZeroAmount() {
    final LineItem instance = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, 0);
    assertThat("Amount should be zero (free).", instance.getAmount(), closeTo(0, 0.001));
  }

  // Not sure why this is supported makes no sense to me.
  // Documented the behavior with this test.
  // TODO: Check with product owner to see if the negative value for the amount should be supported.
  @Test
  public void testConstructorNegativeAmount() {
    final LineItem instance = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, TEST_NEGATIVE_AMOUNT);
    assertThat("Amount should be negative.", instance.getAmount(), closeTo(TEST_NEGATIVE_AMOUNT, 0.001));
  }

  // Keeps IDE or other quality checker from compaining about constructing a new
  // object without capturing its referece.
  private LineItem newLineItem(String SKU, int quantity, double unitPrice) {
    return new LineItem(SKU, quantity, unitPrice);
  }

}
