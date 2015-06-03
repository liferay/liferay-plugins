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

package com.liferay.portal.search.solr.internal.query;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author André de Oliveira
 */
public class SolrPostProcessorTest {

	@Test
	public void testBasicSearch() {
		assertPostProcess("field:word", "field:word", "word");
	}

	@Test
	public void testExactPhrase() {
		assertPostProcess(
			"ddmContent:\"how to create a coupon\"",
			"ddmContent:\"how ? create ? coupon\"",
			"\"how to create a coupon\"");
	}

	@Test
	public void testExactPhraseMismatchingKeywords() {
		assertPostProcess(
			"field:\"how ? create ? coupon\"",
			"field:\"how ? create ? coupon\"", "\"coupon to create a how\"");
	}

	@Test
	public void testExactPhraseMixedWithTerms() {
		assertPostProcess(
			"how:\"to fix a car\" some:terms name:\"Snacks on the house\"",
			"how:\"? fix ? car\" some:terms name:\"Snacks ? ? house\"",
			"\"to fix a car\" terms \"Snacks on the house\"");
	}

	@Test
	public void testExactPhraseWithoutStopWords() {
		assertPostProcess(
			"k:\"Liferay rocks\"", "k:\"Liferay rocks\"", "\"Liferay rocks\"");
	}

	@Test
	public void testExactPhraseWithRepetition() {
		assertPostProcess(
			"one:\"cats and dogs\" two:\"cats and dogs\"",
			"one:\"cats ? dogs\" two:\"cats ? dogs\"", "\"cats and dogs\"");
	}

	@Test
	public void testQuestionMarkOutsideExactPhrase() {
		assertPostProcess(
			"one:\"how to\" two:x?z three:\"yes the sir\"",
			"one:\"how to\" two:x?z three:\"yes ? sir\"",
			"\"how to\" x?z \"yes the sir\"");
	}

	@Test
	public void testUnclosedQuotes() {
		assertPostProcess("field:how\"to", "field:how\"to", "how\"to");
	}

	protected void assertPostProcess(
		String expected, String query, String keywords) {

		SolrPostProcesor solrPostProcesor = new SolrPostProcesor(
			query, keywords);

		String actual = solrPostProcesor.postProcess();

		Assert.assertEquals(expected, actual);
	}

}