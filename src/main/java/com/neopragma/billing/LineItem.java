package com.neopragma.billing;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Currency;
import java.util.Locale;

/**
 * Immutable object representing a line item entry on an invoice.
 *
 * @since 1.0
 */
public class LineItem {

	private final Sku sku;
	private final int itemQuantity;
	private final BigDecimal unitPrice;

	/**
	 * Construct a new instance of {@code LineItem}.
	 *
	 * @param sku the SKU code for the item
	 * @param quantity the number of items (must be positive)
	 * @param unitPrice the unit price for each item
	 * @throws NullPointerException if {@code sku} is {@code null}
	 * @throws IllegalArgumentException if (@code quantity} &lt; 1
	 * @deprecated as of 1.1 use {@link LineItem#LineItem(com.neopragma.billing.Sku, int, BigDecimal) )
	 */
	@Deprecated
	public LineItem(final String sku, final int quantity, final double unitPrice) {
		this(new Sku(sku), quantity, new BigDecimal(unitPrice));
	}

	/**
	 * Construct a new instance of {@code LineItem}.
	 *
	 * @param sku the SKU code for the item
	 * @param quantity the number of items (must be positive)
	 * @param unitPrice the unit price for each item
	 * @throws NullPointerException if {@code sku} is {@code null}
	 * @throws IllegalArgumentException if (@code quantity} &lt; 1
	 */
	public LineItem(final Sku sku, final int quantity, final BigDecimal unitPrice) {
		Preconditions.checkNotNull(sku, "sku == null");
		Preconditions.checkNotNull(unitPrice, "unitPrice == null");
		Preconditions.checkArgument(quantity != 0, "Quantity cannot be zero.");
		Preconditions.checkArgument(unitPrice.compareTo(BigDecimal.ZERO) >= 0, "unitPrice < 0");
		this.sku = sku;
		this.itemQuantity = quantity;
		final Currency currency = Currency.getInstance(Locale.getDefault(Locale.Category.FORMAT));
		this.unitPrice = unitPrice.setScale(currency.getDefaultFractionDigits(), RoundingMode.HALF_EVEN);
	}

	/**
	 * Get the unit price for the line item as {@code double}.
	 *
	 * @return the unit price for the line item as {@code double}
	 * @deprecated as of 1.1 use {@link #getItemUnitPrice()}
	 */
	@Deprecated
	public double getAmount() {
		return unitPrice.doubleValue();
	}

	/**
	 * Get the unit price for the line item.
	 *
	 * @return the unit price for the line item
	 * @since 1.1
	 */
	public BigDecimal getItemUnitPrice() {
		return unitPrice;
	}

	/**
	 * Get the quantity of items for the line as a {@code double}.
	 *
	 * @return the number of items for the line as a {@code double}
	 * @deprecated as of 1.1 use {@link #getItemQuantity()}
	 */
	@Deprecated
	public double getQuantity() {
		return itemQuantity;
	}

	/**
	 * Get the quantity of items for the line
	 *
	 * @return the number of items for the line
	 * @since 1.1
	 */
	public int getItemQuantity() {
		return itemQuantity;
	}

	/**
	 * Get the product SKU code as a string.
	 *
	 * @return the product SKU code as a string
	 */
	public String getSKU() {
		return sku.toString();
	}

	/**
	 * Get the product SKU code.
	 *
	 * @return the product SKU code
	 * @since 1.1
	 */
	public Sku getItemSKU() {
		return sku;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		final LineItem other = (LineItem) obj;
		return getItemQuantity() == other.getItemQuantity()
				&& Objects.equal(getItemUnitPrice(), other.getItemUnitPrice())
				&& Objects.equal(getItemSKU(), other.getItemSKU());
	}

	@Override
	public int hashCode() {
		return 7 + 37 * Objects.hashCode(sku, itemQuantity, unitPrice);
	}

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(getClass())
				.add("SKU", sku)
				.add("itemQuantity", itemQuantity)
				.add("unitPrice", unitPrice)
				.toString();
	}

	/**
	 * Get the total cost for the items.
	 *
	 * This is the quantity multiplied by the unit price.
	 *
	 * @return the total cost for the items
	 * @since 1.1
	 */
	public BigDecimal getCost() {
		return getItemUnitPrice().multiply(new BigDecimal(getItemQuantity()));
	}

	/**
	 * Determine if the {@code LineItem} represents a return of items.
	 *
	 * @return true if the {@code LineItem} represents a return of items
	 * @since 1.1
	 */
	public boolean isReturn() {
		return itemQuantity < 0;
	}
}
