package com.neopragma.billing;

import com.google.common.base.Preconditions;
import java.util.Collection;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Immutable Stock Keeping Unit code modeled as list of four string
 * elements in order.
 *
 * Stock Keeping Unit (SKU) is a business domain concept. SKUs for our business
 * follow this pattern: XXX-NN-XX-NNNNN, where X is an upper-case letter and N
 * is a number. For example, ABC-09-XY-45027.
 *
 * @since 1.1
 */
public class Sku {

	private static final Pattern SKU_PATTERN = Pattern.compile("([A-Z]{3})-([0-9]{2})-([A-Z]{2})-([0-9]{5})");
	private final String cachedToString;

	/**
	 * Construct a new instance of {@code Sku} by parsing a string.
	 *
	 * @param skuString the string containing the SKU code
	 * @throws NullPointerException if {@code skuString} is null
	 * @throws IllegalArgumentException if {@code skuString} is not a valid SKU
	 * code
	 */
	public Sku(final String skuString) {
		Preconditions.checkNotNull(skuString, "skuString == null");
		final Matcher matcher = SKU_PATTERN.matcher(skuString);
		Preconditions.checkArgument(matcher.find(), "Malformed SKU: " + skuString);
		cachedToString = skuString;
	}

	/**
	 * Construct a new instance of {@code Sku} provided groupings of SKU
	 * components.
	 *
	 * The {@code subsections} may be made up of any ordered component groupings
	 * of the SKU as long as they were separated at the dash locations.
	 *
	 * @param subSections any component groupings of the SKU
	 * @throws NullPointerException if {@code subSections} is null
	 * @throws IllegalArgumentException if the {@code subSections} do not build
	 * a valid SKU code
	 */
	public Sku(final String... subSections) {
		this(Stream.of(subSections).collect(Collectors.joining("-")));
	}

	/**
	 * Construct a new instance of {@code Sku} provided a collection of SKU
	 * components.
	 *
	 * The {@code subsections} may be made up of a collection of ordered
	 * component groupings of the SKU as long as they were separated at the dash
	 * locations.
	 *
	 * @param subSections any component groupings of the SKU
	 * @throws NullPointerException if {@code subSections} is null
	 * @throws IllegalArgumentException if the {@code subSections} do not build
	 * a valid SKU code
	 */
	public Sku(final Collection<String> subSections) {
		this(subSections.stream().collect(Collectors.joining("-")));
	}

	/**
	 * Construct a new instance of {@code Sku} provided specific SKU components.
	 *
	 * @param part1 a string consisting of three upper-case English letters
	 * @param part2 an integer value from zero up to 99
	 * @param part3 a string consisting of two upper-case English letters
	 * @param part4 an integer value from zero up to 99999
	 * @throws IllegalArgumentException if the parameters do not build a valid
	 * SKU code
	 */
	public Sku(final String part1, final int part2, final String part3, final int part4) {
		this(String.format("%s-%02d-%s-%05d", part1, part2, part3, part4));
	}

	@Override
	public String toString() {
		return cachedToString;
	}

	@Override
	public int hashCode() {
		return cachedToString.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || (obj != null
				&& getClass() == obj.getClass()
				&& Objects.equals(toString(), obj.toString()));
	}

}
