/**
 * Copyright (c) 2000-2013 Liferay, Inc. All rights reserved.
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

package com.liferay.repository.external;

/**
 * This class holds results to be returned by the {@link
 * ExtRepository#search(com.liferay.portal.kernel.search.SearchContext,
 * com.liferay.portal.kernel.search.Query,
 * com.liferay.repository.external.search.ExtRepositoryQueryMapper)} method.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositorySearchResult<T extends ExtRepositoryObject> {

	/**
	 * Create a search result with the given ext repository object, score and
	 * snippet.
	 *
	 * @param object the ext repository object found by the search
	 * @param score the score assigned to the ext repository object by the
	 *        search engine (must be between 0 and 1)
	 * @param snippet the snippet to be used for highlighting when showing the
	 *        search results in the UI
	 */
	public ExtRepositorySearchResult(T object, float score, String snippet) {
		_object = object;
		_score = score;
		_snippet = snippet;
	}

	/**
	 * Gets the ext repository object associated to this search result.
	 *
	 * @return the ext repository object associated to this search result
	 */
	public T getObject() {
		return _object;
	}

	/**
	 * Gets the score associated to this search result.
	 *
	 * @return a number between 0 and 1
	 */
	public float getScore() {
		return _score;
	}

	/**
	 * Get the snippet used for highlighting when showing the search results in
	 * the UI.
	 *
	 * @return the text to highlight
	 */
	public String getSnippet() {
		return _snippet;
	}

	private T _object;
	private float _score;
	private String _snippet;

}