package com.neopragma.billing;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.List;

/**
 * A group of {@code LineItem} objects representing an invoice.
 *
 * @since 1.0
 */
public class Invoice {

	// TODO: Check with product owner about typical size of most invoices.
	public static final int TYPICAL_SIZE = 10;

	// Use ArrayList over LinkedList because it always out performs LinkedList.
	// Costa, Diego et. al. "Emperical Study of Usage and Performance of Java
	//   Collections", ICPE '17, Proceedings of the 8th ACM/SPEC on International
	//   Conference on Performance Engineering, 22 April 2017, pp 389-400
	private final List<LineItem> lineItems = new ArrayList<>(TYPICAL_SIZE);
	private double total;

	/**
	 * Add an new item to the invoice.
	 *
	 * @param lineItem the item to add to the invoice
	 */
	public void add(final LineItem lineItem) {
		Preconditions.checkNotNull(lineItem, "lineItem == null");
		lineItems.add(lineItem);
		total += lineItem.getCost();
	}

	/**
	 * Get the total for the invoice.
	 *
	 * @return the total for the invoice
	 */
	public double getTotal() {
		return total;
	}

	/**
	 * Get the number of items in the invoice.
	 *
	 * @return the number of items in the invoice
	 */
	public int count() {
		return lineItems.size();
	}

}
