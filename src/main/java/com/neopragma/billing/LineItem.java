package com.neopragma.billing;

import com.google.common.base.MoreObjects;
import com.google.common.base.Preconditions;

/**
 * Immutable object representing a line item entry on an invoice.
 *
 * @since 1.0
 */
public class LineItem {

	private final String sku;
	private final int itemQuantity;
	private final double unitPrice;

	/**
	 * Construct a new instance of {@code LineItem}.
	 *
	 * @param sku the SKU number for the item
	 * @param quantity the number of items (must be positive)
	 * @param unitPrice the unit price for each item
	 * @throws NullPointerException if {@code sku} is {@code null}
	 * @throws IllegalArgumentException if (@code quantity} &lt; 1
	 */
	public LineItem(final String sku, final int quantity, final double unitPrice) {
		Preconditions.checkNotNull(sku, "sku == null");
		Preconditions.checkArgument(quantity > 0, "Quantity must be greater than zero.");
		this.sku = sku;
		this.itemQuantity = quantity;
		this.unitPrice = unitPrice;
	}

	/**
	 * Get the unit price for the line.
	 *
	 * @return the unit price for the line
	 */
	public double getAmount() {
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
	 * Get the product SKU identity.
	 *
	 * @return the product SKU identity
	 */
	public String getSKU() {
		return sku;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final LineItem other = (LineItem) obj;
		if (getItemQuantity() != other.getItemQuantity()) {
			return false;
		}
		if (Double.doubleToLongBits(getAmount()) != Double.doubleToLongBits(other.getAmount())) {
			return false;
		}
		return getSKU() == null ? other.getSKU() == null : getSKU().equals(other.getSKU());
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + (this.sku != null ? this.sku.hashCode() : 0);
		hash = 37 * hash + this.itemQuantity;
		hash = 37 * hash + (int) (Double.doubleToLongBits(this.unitPrice) ^ (Double.doubleToLongBits(this.unitPrice) >>> 32));
		return hash;
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
	public double getCost() {
		return getItemQuantity() * getAmount();
	}
}
