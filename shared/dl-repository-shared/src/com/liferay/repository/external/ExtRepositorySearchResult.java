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
 * Holds search results to be returned by the {@link
 * ExtRepository#search(SearchContext, Query, ExtRepositoryQueryMapper)} method.
 *
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositorySearchResult<T extends ExtRepositoryObject> {

	/**
	 * Creates the search result matching the external repository object, score,
	 * and snippet.
	 *
	 * @param object the repository object found by the search (file, folder, or
	 *        both)
	 * @param score the score (between <code>0</code> and <code>1</code>)
	 *        assigned to the repository object by the search engine.
	 * @param snippet the snippet used for highlighting when displaying the
	 *        search results in the UI
	 */
	public ExtRepositorySearchResult(T object, float score, String snippet) {
		_object = object;
		_score = score;
		_snippet = snippet;
	}

	/**
	 * Returns the external repository object associated with the search result.
	 *
	 * @return the external repository object associated with the search result
	 */
	public T getObject() {
		return _object;
	}

	/**
	 * Returns the score (between <code>0</code> and <code>1</code>) associated
	 * with the search result.
	 *
	 * @return the score associated with the search result
	 */
	public float getScore() {
		return _score;
	}

	/**
	 * Returns the text snippet to highlight when displaying the search results
	 * in the UI.
	 *
	 * @return the text snippet to highlight when displaying the search results
	 *         in the UI
	 */
	public String getSnippet() {
		return _snippet;
	}

	private T _object;
	private float _score;
	private String _snippet;

}