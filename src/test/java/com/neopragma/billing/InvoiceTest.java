package com.neopragma.billing;

import java.util.UUID;
import static org.junit.Assert.*;
import org.junit.Test;

public class InvoiceTest {
	private final String TEST_SKU = UUID.randomUUID().toString();

	@Test
	public void test() {
		Invoice invoice = new Invoice();
		invoice.add(new LineItem(TEST_SKU, 1, 15.0d));
		assertEquals(1, invoice.count());
	}

	@Test
	public void test2() {
		Invoice invoice = new Invoice();
		invoice.add(new LineItem(TEST_SKU, 1, 15.0d));
		invoice.add(new LineItem(TEST_SKU, 3, 14.0d));
		assertEquals(57.0d, invoice.getTotal(), 0.005d);
	}

}
