package com.neopragma.billing;

import java.util.UUID;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class InvoiceTest {

	private final String TEST_SKU = UUID.randomUUID().toString();

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
	public void testZeroItemTotal() {
		assertEquals("Unexpected total value.", 0.0d, invoice.getTotal(), 0.005d);
	}

	@Test
	public void testAddOneItemCount() {
		invoice.add(new LineItem(TEST_SKU, 1, 15.0d));
		assertEquals("Unexpected count.", 1, invoice.count());
	}

	@Test
	public void testAddOneItemTotal() {
		invoice.add(new LineItem(TEST_SKU, 1, 15.0d));
		assertEquals("Unexpected total value.", 15.0d, invoice.getTotal(), 0.005d);
	}

	@Test
	public void testAddTwoItemsTotal() {
		invoice.add(new LineItem(TEST_SKU, 1, 15.0d));
		invoice.add(new LineItem(TEST_SKU, 3, 14.0d));
		assertEquals("Unexpected total value.", 57.0d, invoice.getTotal(), 0.005d);
	}

	@Test
	public void testAddTwoItemsCount() {
		invoice.add(new LineItem(TEST_SKU, 1, 15.0d));
		invoice.add(new LineItem(TEST_SKU, 3, 14.0d));
		assertEquals("Unexpected total value.", 2, invoice.count());
	}

	@Test(expected = NullPointerException.class)
	public void testAddNull() {
		invoice.add(null);
	}
}
