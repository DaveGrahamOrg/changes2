package com.neopragma.billing;

import org.junit.AfterClass;
import org.junit.BeforeClass;

/**
 * Run the Invoice test with the total on add feature switch enabled.
 */
public class InvoiceTotalOnAddTest extends InvoiceTest {

	@BeforeClass
	public static void beforeClass() {
		System.setProperty(Invoice.FEATURE_FLAG_INVOICE_TOTAL_CALCULATED_ON_ADD, Boolean.TRUE.toString());
	}

	@AfterClass
	public static void afterClass() {
		System.setProperty(Invoice.FEATURE_FLAG_INVOICE_TOTAL_CALCULATED_ON_ADD, Boolean.FALSE.toString());
	}
}
