/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.portal.search.solr;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Bryan Engler
 * @author André de Oliveira
 */
public class SolrExpandoPostProcessorTest {

	@Test
	public void testExpandoFieldsWithoutSpaces() {
		assertPostProcess(
			"expando/custom_fields/field1:x expando/custom_fields/field2:y",
			"expando/custom_fields/field1:x expando/custom_fields/field2:y");
	}

	@Test
	public void testExpandoFieldsWithSpaces() {
		assertPostProcess(
			"expando/custom_fields/space\\ field\\ a:x " +
				"expando/custom_fields/space\\ field\\ b:y",
			"expando/custom_fields/space field a:x " +
				"expando/custom_fields/space field b:y");
	}

	@Test
	public void testNonExpandoFields() {
		assertPostProcess("field1:x field2:y", "field1:x field2:y");
	}

	@Test
	public void testNonExpandoFieldsAndExpandoFieldsWithOrWithoutSpaces() {
		assertPostProcess(
			"field1:x expando/custom_fields/space\\ field\\ a:x field2:y " +
				"expando/custom_fields/space\\ field\\ b:x field3:z",
			"field1:x expando/custom_fields/space field a:x field2:y " +
				"expando/custom_fields/space field b:x field3:z");
	}

	protected void assertPostProcess(String expected, String query) {
		SolrExpandoPostProcessor solrExpandoPostProcessor =
			new SolrExpandoPostProcessor(query);

		String actual = solrExpandoPostProcessor.postProcess();

		Assert.assertEquals(expected, actual);
	}

}