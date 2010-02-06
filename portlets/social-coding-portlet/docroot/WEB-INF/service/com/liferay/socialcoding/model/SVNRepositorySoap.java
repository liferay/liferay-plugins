/**
 * Copyright (c) 2000-2010 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.socialcoding.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="SVNRepositorySoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class SVNRepositorySoap implements Serializable {
	public static SVNRepositorySoap toSoapModel(SVNRepository model) {
		SVNRepositorySoap soapModel = new SVNRepositorySoap();

		soapModel.setSvnRepositoryId(model.getSvnRepositoryId());
		soapModel.setUrl(model.getUrl());
		soapModel.setRevisionNumber(model.getRevisionNumber());

		return soapModel;
	}

	public static SVNRepositorySoap[] toSoapModels(SVNRepository[] models) {
		SVNRepositorySoap[] soapModels = new SVNRepositorySoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SVNRepositorySoap[][] toSoapModels(SVNRepository[][] models) {
		SVNRepositorySoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SVNRepositorySoap[models.length][models[0].length];
		}
		else {
			soapModels = new SVNRepositorySoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SVNRepositorySoap[] toSoapModels(List<SVNRepository> models) {
		List<SVNRepositorySoap> soapModels = new ArrayList<SVNRepositorySoap>(models.size());

		for (SVNRepository model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SVNRepositorySoap[soapModels.size()]);
	}

	public SVNRepositorySoap() {
	}

	public long getPrimaryKey() {
		return _svnRepositoryId;
	}

	public void setPrimaryKey(long pk) {
		setSvnRepositoryId(pk);
	}

	public long getSvnRepositoryId() {
		return _svnRepositoryId;
	}

	public void setSvnRepositoryId(long svnRepositoryId) {
		_svnRepositoryId = svnRepositoryId;
	}

	public String getUrl() {
		return _url;
	}

	public void setUrl(String url) {
		_url = url;
	}

	public long getRevisionNumber() {
		return _revisionNumber;
	}

	public void setRevisionNumber(long revisionNumber) {
		_revisionNumber = revisionNumber;
	}

	private long _svnRepositoryId;
	private String _url;
	private long _revisionNumber;
}