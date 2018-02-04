package com.neopragma.billing;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Test of Sku class.
 */
public class SkuTest {

	private static final String TEST_PART_1 = "ABC";
	private static final String TEST_PART_2 = "09";
	private static final int TEST_PART_2_INT = 9;
	private static final String TEST_PART_3 = "XY";
	private static final String TEST_PART_4 = "05027";
	private static final int TEST_PART_4_INT = 5027;
	private static final String TEST_SKU_STRING
			= TEST_PART_1 + "-" + TEST_PART_2 + "-" + TEST_PART_3 + "-" + TEST_PART_4;
	private static final List<String> TEST_COLLECTION_ALL_COMPONENTS
			= Arrays.asList(TEST_PART_1, TEST_PART_2, TEST_PART_3, TEST_PART_4);
	private static final String TEST_ALT_PART_1 = "DEF";
	private static final String TEST_ALT_PART_2 = "45";
	private static final String TEST_ALT_PART_3 = "WV";
	private static final String TEST_ALT_PART_4 = "83428";

	/**
	 * Test of getPart2AsInt method, of class Sku.
	 */
	@Test
	public void testGetPart2AsInt() {
		final Sku instance = new Sku(TEST_SKU_STRING);
		assertThat("Wrong integer value.", instance.getPart2AsInt(), is(TEST_PART_2_INT));
	}

	/**
	 * Test of getPart4AsInt method, of class Sku.
	 */
	@Test
	public void testGetPart4AsInt() {
		final Sku instance = new Sku(TEST_SKU_STRING);
		assertThat("Wrong integer value.", instance.getPart4AsInt(), is(TEST_PART_4_INT));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToString() {
		final Sku instance = new Sku(TEST_SKU_STRING);
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringArrayConstructor_1_2_3_4() {
		final Sku instance = new Sku(TEST_PART_1, TEST_PART_2, TEST_PART_3, TEST_PART_4);
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringArrayConstructor_12_3_4() {
		final Sku instance = new Sku(TEST_PART_1 + "-" + TEST_PART_2, TEST_PART_3, TEST_PART_4);
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringArrayConstructor_1_23_4() {
		final Sku instance = new Sku(TEST_PART_1, TEST_PART_2 + "-" + TEST_PART_3, TEST_PART_4);
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringArrayConstructor_1_2_34() {
		final Sku instance = new Sku(TEST_PART_1, TEST_PART_2, TEST_PART_3 + "-" + TEST_PART_4);
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringArrayConstructor_123_4() {
		final Sku instance = new Sku(TEST_PART_1 + "-" + TEST_PART_2 + "-" + TEST_PART_3, TEST_PART_4);
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringArrayConstructor_1_234() {
		final Sku instance = new Sku(TEST_PART_1, TEST_PART_2 + "-" + TEST_PART_3 + "-" + TEST_PART_4);
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringArrayConstructor_1234() {
		String[] array = new String[]{TEST_SKU_STRING};
		final Sku instance = new Sku(array);
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringCollectionConstructor_1_2_3_4() {
		final Sku instance = new Sku(Arrays.asList(TEST_PART_1, TEST_PART_2, TEST_PART_3, TEST_PART_4));
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringCollectionConstructor_12_3_4() {
		final Sku instance = new Sku(Arrays.asList(TEST_PART_1 + "-" + TEST_PART_2, TEST_PART_3, TEST_PART_4));
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringCollectionConstructor_1_23_4() {
		final Sku instance = new Sku(Arrays.asList(TEST_PART_1, TEST_PART_2 + "-" + TEST_PART_3, TEST_PART_4));
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringCollectionConstructor_1_2_34() {
		final Sku instance = new Sku(Arrays.asList(TEST_PART_1, TEST_PART_2, TEST_PART_3 + "-" + TEST_PART_4));
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringCollectionConstructor_123_4() {
		final Sku instance = new Sku(Arrays.asList(TEST_PART_1 + "-" + TEST_PART_2 + "-" + TEST_PART_3, TEST_PART_4));
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringCollectionConstructor_1_234() {
		final Sku instance = new Sku(Arrays.asList(TEST_PART_1, TEST_PART_2 + "-" + TEST_PART_3 + "-" + TEST_PART_4));
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	/**
	 * Test of toString method, of class Sku.
	 */
	@Test
	public void testToStringCollectionConstructor_1234() {
		final Sku instance = new Sku(Arrays.asList(TEST_SKU_STRING));
		assertThat("Wrong integer value.", instance.toString(), is(TEST_SKU_STRING));
	}

	@Test
	public void testListContent() {
		final Sku instance = new Sku(TEST_SKU_STRING);
		assertThat("Unexpected content.", instance, contains(TEST_PART_1, TEST_PART_2, TEST_PART_3, TEST_PART_4));
	}

	@Test
	public void testListContentArray() {
		final Sku instance = new Sku(Arrays.asList(TEST_SKU_STRING));
		assertThat("Unexpected content.", instance, contains(TEST_PART_1, TEST_PART_2, TEST_PART_3, TEST_PART_4));
	}

	@Test
	public void testListContentParameters() {
		final Sku instance = new Sku(TEST_PART_1, TEST_PART_2_INT, TEST_PART_3, TEST_PART_4_INT);
		assertThat("Unexpected content.", instance, contains(TEST_PART_1, TEST_PART_2, TEST_PART_3, TEST_PART_4));
	}

	@Test
	public void testListContentCollection() {
		final Sku instance = new Sku(TEST_COLLECTION_ALL_COMPONENTS);
		assertThat("Unexpected content.", instance, contains(TEST_PART_1, TEST_PART_2, TEST_PART_3, TEST_PART_4));
	}

	/**
	 * Test of hashCode method, of class Sku.
	 */
	@Test
	public void testHashCode() {
		assertThat("Failed hashcode check", new Sku(TEST_SKU_STRING).hashCode(), is(new Sku(TEST_COLLECTION_ALL_COMPONENTS).hashCode()));
	}

	/**
	 * Test of hashCode method, of class Sku.
	 */
	@Test
	public void testHashCodeFailPart1() {
		assertThat("Failed hashcode check", new Sku(TEST_SKU_STRING).hashCode(), is(not(equalTo(new Sku(TEST_ALT_PART_1, TEST_PART_2, TEST_PART_3, TEST_PART_4).hashCode()))));
	}

	/**
	 * Test of hashCode method, of class Sku.
	 */
	@Test
	public void testHashCodeFailPart2() {
		assertThat("Failed hashcode check", new Sku(TEST_SKU_STRING).hashCode(), is(not(equalTo(new Sku(TEST_PART_1, TEST_ALT_PART_2, TEST_PART_3, TEST_PART_4).hashCode()))));
	}

	/**
	 * Test of hashCode method, of class Sku.
	 */
	@Test
	public void testHashCodeFailPart3() {
		assertThat("Failed hashcode check", new Sku(TEST_SKU_STRING).hashCode(), is(not(equalTo(new Sku(TEST_PART_1, TEST_PART_2, TEST_ALT_PART_3, TEST_PART_4).hashCode()))));
	}

	/**
	 * Test of hashCode method, of class Sku.
	 */
	@Test
	public void testHashCodeFailPart4() {
		assertThat("Failed hashcode check", new Sku(TEST_SKU_STRING).hashCode(), is(not(equalTo(new Sku(TEST_PART_1, TEST_PART_2, TEST_PART_3, TEST_ALT_PART_4).hashCode()))));
	}

	/**
	 * Test of equals method, of class Sku.
	 */
	@Test
	public void testEquals() {
		assertEquals("Failed hashcode check", new Sku(TEST_SKU_STRING), new Sku(TEST_COLLECTION_ALL_COMPONENTS));
	}

	/**
	 * Test of equals method, of class Sku.
	 */
	@Test
	public void testEqualsFailPart1() {
		assertNotEquals("Failed hashcode check", new Sku(TEST_SKU_STRING), new Sku(TEST_ALT_PART_1, TEST_PART_2, TEST_PART_3, TEST_PART_4));
	}

	/**
	 * Test of equals method, of class Sku.
	 */
	@Test
	public void testEqualsFailPart2() {
		assertNotEquals("Failed hashcode check", new Sku(TEST_SKU_STRING), new Sku(TEST_PART_1, TEST_ALT_PART_2, TEST_PART_3, TEST_PART_4));
	}

	/**
	 * Test of equals method, of class Sku.
	 */
	@Test
	public void testEqualsFailPart3() {
		assertNotEquals("Failed hashcode check", new Sku(TEST_SKU_STRING), new Sku(TEST_PART_1, TEST_PART_2, TEST_ALT_PART_3, TEST_PART_4));
	}

	/**
	 * Test of equals method, of class Sku.
	 */
	@Test
	public void testEqualsFailPart4() {
		assertNotEquals("Failed hashcode check", new Sku(TEST_SKU_STRING), new Sku(TEST_PART_1, TEST_PART_2, TEST_PART_3, TEST_ALT_PART_4));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorBadSkuPart1() {
		newSku("AB", TEST_PART_2, TEST_PART_3, TEST_PART_4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorBadSkuPart2() {
		newSku(TEST_PART_1, "567", TEST_PART_3, TEST_PART_4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorBadSkuPart3() {
		newSku(TEST_PART_1, TEST_PART_2, "RTF", TEST_PART_4);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorBadSkuPart4() {
		newSku(TEST_PART_1, TEST_PART_2, TEST_PART_3, "12");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorBadSkuEmptyCollection() {
		newSku(Collections.emptySet());
	}

	@Test(expected = NullPointerException.class)
	public void testConstrutorArrayNull() {
		final String[] parts = null;
		newSku(parts);
	}

	@Test(expected = NullPointerException.class)
	public void testConstrutorCollectionNull() {
		final Collection<String> parts = null;
		newSku(parts);
	}

	@Test(expected = NullPointerException.class)
	public void testConstrutorNull() {
		final String value = null;
		newSku(value);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorBadSkuEmptyString() {
		newSku("");
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorNullPart1() {
		newSku(null, TEST_PART_2_INT, TEST_PART_3, TEST_PART_4_INT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorNegativePart2() {
		newSku(TEST_PART_1, -14, TEST_PART_3, TEST_PART_4_INT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorNullPart3() {
		newSku(TEST_PART_1, TEST_PART_2_INT, null, TEST_PART_4_INT);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testConstrutorNegativePart3() {
		newSku(TEST_PART_1, TEST_PART_2_INT, TEST_PART_3, -28354);
	}

	private Sku newSku(final String... parts) {
		return new Sku(parts);
	}

	private Sku newSku(final Collection<String> parts) {
		return new Sku(parts);
	}

	private Sku newSku(final String value) {
		return new Sku(value);
	}

	private Sku newSku(final String part1, final int part2, final String part3, final int part4) {
		return new Sku(part1, part2, part3, part4);
	}
}
