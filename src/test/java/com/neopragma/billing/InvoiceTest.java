package com.neopragma.billing;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class InvoiceTest {

	private final Currency CURRENCY = Currency.getInstance(Locale.getDefault(Locale.Category.FORMAT));
	private final Sku TEST_SKU = new Sku("ABC-09-XY-45027");
	private final BigDecimal TEST_UNIT_PRICE_1 = new BigDecimal(15).setScale(CURRENCY.getDefaultFractionDigits());
	private final BigDecimal TEST_UNIT_PRICE_2 = new BigDecimal(14).setScale(CURRENCY.getDefaultFractionDigits());
	private final BigDecimal TEST_EXPECTED_TOTAL = new BigDecimal(57).setScale(CURRENCY.getDefaultFractionDigits());

	private Invoice invoice;

	@Before
	public void before() {
		invoice = new Invoice();
	}

	@Test
	public void testZeroItemCount() {
		assertEquals("Unexpected count.", 0, invoice.count());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testZeroItemTotal() {
		assertEquals("Unexpected total value.", 0.0d, invoice.getTotal(), 0.005d);
	}

	@Test
	public void testAddOneItemCount() {
		invoice.add(new LineItem(TEST_SKU, 1, TEST_UNIT_PRICE_1));
		assertEquals("Unexpected count.", 1, invoice.count());
	}

	@Test
	public void testAddOneItemInvoiceTotal() {
		invoice.add(new LineItem(TEST_SKU, 1, TEST_UNIT_PRICE_1));
		assertEquals("Unexpected total value.", TEST_UNIT_PRICE_1, invoice.getInvoiceTotal());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testAddTwoItemsInvoiceTotal() {
		invoice.add(new LineItem(TEST_SKU, 1, TEST_UNIT_PRICE_1));
		invoice.add(new LineItem(TEST_SKU, 3, TEST_UNIT_PRICE_2));
		assertEquals("Unexpected total value.", TEST_EXPECTED_TOTAL, invoice.getInvoiceTotal());
	}

	@Test
	@SuppressWarnings("deprecation")
	public void testAddTwoItemsTotal() {
		invoice.add(new LineItem(TEST_SKU, 1, TEST_UNIT_PRICE_1));
		invoice.add(new LineItem(TEST_SKU, 3, TEST_UNIT_PRICE_2));
		assertEquals("Unexpected total value.", 57.0d, invoice.getTotal(), 0.005d);
	}

	@Test
	public void testAddTwoItemsCount() {
		invoice.add(new LineItem(TEST_SKU, 1, TEST_UNIT_PRICE_1));
		invoice.add(new LineItem(TEST_SKU, 3, TEST_UNIT_PRICE_2));
		assertEquals("Unexpected total value.", 2, invoice.count());
	}

	@Test(expected = NullPointerException.class)
	public void testAddNull() {
		invoice.add(null);
	}
}
