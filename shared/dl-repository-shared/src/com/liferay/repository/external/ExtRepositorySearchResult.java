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
 * @author Iván Zaera
 * @author Sergio González
 */
public class ExtRepositorySearchResult<T extends ExtRepositoryObject> {

	public ExtRepositorySearchResult(T object, float score, String snippet) {
		_object = object;
		_score = score;
		_snippet = snippet;
	}

	public T getObject() {
		return _object;
	}

	public float getScore() {
		return _score;
	}

	public String getSnippet() {
		return _snippet;
	}

	private T _object;
	private float _score;
	private String _snippet;

}