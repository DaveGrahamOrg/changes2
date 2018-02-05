package com.neopragma.billing;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Locale;

/**
 * A group of {@code LineItem} objects representing an invoice.
 *
 * @since 1.0
 */
public class Invoice {

	// TODO: Check with product owner about typical size of most invoices.
	public static final int TYPICAL_SIZE = 10;

	// Use ArrayList over LinkedList because ArrayList out performs LinkedList.
	// Costa, Diego et. al. "Emperical Study of Usage and Performance of Java
	//   Collections", ICPE '17, Proceedings of the 8th ACM/SPEC on International
	//   Conference on Performance Engineering, 22 April 2017, pp 389-400
	//   https://research.spec.org/icpe_proceedings/2017/proceedings/p389.pdf
	private final List<LineItem> lineItems = new ArrayList<>(TYPICAL_SIZE);
	private BigDecimal total;

	/**
	 * Create a new instance of {@code Invoice}.
	 */
	public Invoice() {
		final Currency currency = Currency.getInstance(Locale.getDefault(Locale.Category.FORMAT));
		total = new BigDecimal(0).setScale(currency.getDefaultFractionDigits());
	}

	/**
	 * Add an new item to the invoice.
	 *
	 * @param lineItem the item to add to the invoice
	 */
	public void add(final LineItem lineItem) {
		Preconditions.checkNotNull(lineItem, "lineItem == null");
		lineItems.add(lineItem);
		total = total.add(lineItem.getCost());
	}

	/**
	 * Get the total for the invoice.
	 *
	 * @return the total for the invoice
	 * @deprecated as of 1.1 use {@link #getInvoiceTotal()}
	 */
	public double getTotal() {
		return getInvoiceTotal().doubleValue();
	}

	/**
	 * Get the total for the invoice.
	 *
	 * @return the total for the invoice
	 * @since 1.1
	 */
	public BigDecimal getInvoiceTotal() {
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

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
				.add("lineItems", lineItems)
				.add("total", getInvoiceTotal())
				.toString();
	}

}
