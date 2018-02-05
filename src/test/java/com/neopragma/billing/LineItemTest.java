package com.neopragma.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;
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

	private final Currency CURRENCY = Currency.getInstance(Locale.getDefault(Locale.Category.FORMAT));
	private final BigDecimal TEST_POSTITIVE_AMOUNT = new BigDecimal(40d * Math.random() + 1d).setScale(CURRENCY.getDefaultFractionDigits(), RoundingMode.HALF_EVEN);
	private final BigDecimal TEST_NEGATIVE_AMOUNT = new BigDecimal(-22d * Math.random() - 1d).setScale(CURRENCY.getDefaultFractionDigits(), RoundingMode.HALF_EVEN);
	private final int TEST_POSITIVE_QUANTITY = (int) (12d * Math.random()) + 1;
	private final int TEST_NEGATIVE_QUANTITY = (int) (-17d * Math.random()) - 1;
	private final Sku TEST_SKU = new Sku("ABC-09-XY-45027");
	private final Sku TEST_ALT_SKU = new Sku("DEF-09-XY-45027");
	private final LineItem INSTANCE = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, TEST_POSTITIVE_AMOUNT);
	private final BigDecimal TEST_TOTAL_COST = TEST_POSTITIVE_AMOUNT.multiply(new BigDecimal(TEST_POSITIVE_QUANTITY));

	/**
	 * Test of getAmount method, of class LineItem.
	 */
	@Test
	@SuppressWarnings("deprecation")
	public void testGetAmount() {
		assertThat("Wrong amount.", INSTANCE.getAmount(), closeTo(TEST_POSTITIVE_AMOUNT.doubleValue(), 0.001));
	}

	/**
	 * Test of getAmount method, of class LineItem.
	 */
	@Test
	public void testGetItemUnitPrice() {
		assertThat("Wrong amount.", INSTANCE.getItemUnitPrice(), is(TEST_POSTITIVE_AMOUNT));
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
		newLineItem("bad SKU", 0, TEST_POSTITIVE_AMOUNT.doubleValue());
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
		final LineItem instance = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, BigDecimal.ZERO);
		assertThat("Amount should be zero (free).", instance.getItemUnitPrice().toString(), is("0.00"));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstructorNegativeAmount() {
		newLineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, TEST_NEGATIVE_AMOUNT);
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
		final LineItem instance = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, BigDecimal.ZERO);
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
		final LineItem instance = new LineItem(TEST_SKU, TEST_POSITIVE_QUANTITY, BigDecimal.ZERO);
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
	private LineItem newLineItem(final Sku SKU, final int quantity, final BigDecimal unitPrice) {
		return new LineItem(SKU, quantity, unitPrice);
	}

	// Keeps IDE or other quality checker from compaining about constructing a new
	// object without capturing its referece.
	@SuppressWarnings("deprecation")
	private LineItem newLineItem(final String SKU, final int quantity, final double unitPrice) {
		return new LineItem(SKU, quantity, unitPrice);
	}

	/**
	 * Test of getCost method, of class LineItem.
	 */
	@Test
	public void testGetCost() {
		assertThat("Cost not calcualted correctly.", INSTANCE.getCost(), is(TEST_TOTAL_COST));
	}

}
