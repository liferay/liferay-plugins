/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
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

package com.liferay.randombibleverse.model;

import java.io.Serializable;

/**
 * @author Brian Wing Shun Chan
 */
public class Bible implements Serializable {

	public Bible() {
	}

	public Bible(String language, String languageName, String versionId) {
		_language = language;
		_languageName = languageName;
		_versionId = versionId;
	}

	public String getLanguage() {
		return _language;
	}

	public void setLanguage(String language) {
		_language = language;
	}

	public String getLanguageName() {
		return _languageName;
	}

	public void setLanguageName(String languageName) {
		_languageName = languageName;
	}

	public String getVersionId() {
		return _versionId;
	}

	public void setVersionId(String versionId) {
		_versionId = versionId;
	}

	private String _language;
	private String _languageName;
	private String _versionId;

}