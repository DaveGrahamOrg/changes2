package com.neopragma.billing;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
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
	private final Sku TEST_SKU = new Sku("ABC-09-XY-45027");
	private final Sku TEST_ALT_SKU = new Sku("DEF-09-XY-45027");
	private final LineItem INSTANCE = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, TEST_POSTITIVE_AMOUNT);
	private final double TEST_TOTAL_COST = TEST_POSTITIVE_AMOUNT * TEST_POSITIVE_QUANTITY;

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
	@SuppressWarnings("deprecation")
	public void testGetQuantity() {
		assertThat("Wrong amount.", INSTANCE.getQuantity(), closeTo(TEST_POSITIVE_QUANTITY, 0.001));
	}

	/**
	 * Test of getSKU method, of class LineItem.
	 */
	@Test
	public void testGetSKU() {
		assertThat("Wrong amount.", INSTANCE.getSKU(), is(TEST_SKU.toString()));
	}

	/**
	 * Test of getItemSKU method, of class LineItem.
	 */
	@Test
	public void testGetItemSKU() {
		assertThat("Wrong amount.", INSTANCE.getItemSKU(), is(TEST_SKU));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorBadSku() {
		newLineItem("bad SKU", 0, TEST_POSTITIVE_AMOUNT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorZeroQuantity() {
		newLineItem(TEST_SKU, 0, TEST_POSTITIVE_AMOUNT);
	}

	@Test(expected = IllegalArgumentException.class)
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

	/**
	 * Test of getItemQuantity method, of class LineItem.
	 */
	@Test
	public void testGetItemQuantity() {
		assertThat("Wrong amount.", INSTANCE.getItemQuantity(), is(TEST_POSITIVE_QUANTITY));
	}

	/**
	 * Test of equals method, of class LineItem.
	 */
	@Test
	public void testEquals() {
		final LineItem instance = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, TEST_POSTITIVE_AMOUNT);
		assertEquals("The LineItems should be equal.", INSTANCE, instance);
	}

	/**
	 * Test of equals method, of class LineItem.
	 */
	@Test
	public void testEqualsFalseQuantity() {
		final LineItem instance = new LineItem(TEST_SKU, 10000, TEST_POSTITIVE_AMOUNT);
		assertNotEquals("The LineItems should not be equal.", INSTANCE, instance);
	}

	/**
	 * Test of equals method, of class LineItem.
	 */
	@Test
	public void testEqualsFalseAmount() {
		final LineItem instance = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, 0);
		assertNotEquals("The LineItems should not be equal.", INSTANCE, instance);
	}

	/**
	 * Test of equals method, of class LineItem.
	 */
	@Test
	public void testEqualsFalseSku() {
		final LineItem instance = new LineItem(TEST_ALT_SKU, TEST_POSITIVE_QUANTITY, TEST_POSTITIVE_AMOUNT);
		assertNotEquals("The LineItems should not be equal.", INSTANCE, instance);
	}

	/**
	 * Test of hashCode method, of class LineItem.
	 */
	@Test
	public void testHashCode() {
		final LineItem instance = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, TEST_POSTITIVE_AMOUNT);
		assertThat("The hashcodes should match.", INSTANCE.hashCode(), is(instance.hashCode()));
	}

	/**
	 * Test of hashCode method, of class LineItem.
	 */
	@Test
	public void testHashCodeNotEqualsQuanity() {
		final LineItem instance = new LineItem(TEST_SKU, 10000, TEST_POSTITIVE_AMOUNT);
		assertNotEquals("The hashcodes should not match.", INSTANCE.hashCode(), instance.hashCode());
	}

	/**
	 * Test of hashCode method, of class LineItem.
	 */
	@Test
	public void testHashCodeNotEqualsAmount() {
		final LineItem instance = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, 0);
		assertNotEquals("The hashcodes should not match.", INSTANCE.hashCode(), instance.hashCode());
	}

	/**
	 * Test of hashCode method, of class LineItem.
	 */
	@Test
	public void testHashCodeNotEqualsSku() {
		final LineItem instance = new LineItem(TEST_ALT_SKU, TEST_POSITIVE_QUANTITY, TEST_POSTITIVE_AMOUNT);
		assertNotEquals("The hashcodes should not match.", INSTANCE.hashCode(), instance.hashCode());
	}

	// Keeps IDE or other quality checker from compaining about constructing a new
	// object without capturing its referece.
	private LineItem newLineItem(Sku SKU, int quantity, double unitPrice) {
		return new LineItem(SKU, quantity, unitPrice);
	}

	// Keeps IDE or other quality checker from compaining about constructing a new
	// object without capturing its referece.
	@SuppressWarnings("deprecation")
	private LineItem newLineItem(String SKU, int quantity, double unitPrice) {
		return new LineItem(SKU, quantity, unitPrice);
	}

	/**
	 * Test of getCost method, of class LineItem.
	 */
	@Test
	public void testGetCost() {
		assertThat("Amount should be zero (free).", INSTANCE.getCost(), closeTo(TEST_TOTAL_COST, 0.001));
	}

}
