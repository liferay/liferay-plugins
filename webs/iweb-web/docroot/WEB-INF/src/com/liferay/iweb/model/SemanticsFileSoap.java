/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
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

package com.liferay.iweb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="SemanticsFileSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsFileSoap implements Serializable {
	public static SemanticsFileSoap toSoapModel(SemanticsFile model) {
		SemanticsFileSoap soapModel = new SemanticsFileSoap();

		soapModel.setSemanticsName(model.getSemanticsName());
		soapModel.setSemanticsURI(model.getSemanticsURI());
		soapModel.setCreatedInInterestGroup(model.getCreatedInInterestGroup());

		return soapModel;
	}

	public static SemanticsFileSoap[] toSoapModels(List<SemanticsFile> models) {
		List<SemanticsFileSoap> soapModels = new ArrayList<SemanticsFileSoap>(models.size());

		for (SemanticsFile model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SemanticsFileSoap[soapModels.size()]);
	}

	public SemanticsFileSoap() {
	}

	public String getPrimaryKey() {
		return _semanticsURI;
	}

	public void setPrimaryKey(String pk) {
		setSemanticsURI(pk);
	}

	public String getSemanticsName() {
		return _semanticsName;
	}

	public void setSemanticsName(String semanticsName) {
		_semanticsName = semanticsName;
	}

	public String getSemanticsURI() {
		return _semanticsURI;
	}

	public void setSemanticsURI(String semanticsURI) {
		_semanticsURI = semanticsURI;
	}

	public long getCreatedInInterestGroup() {
		return _createdInInterestGroup;
	}

	public void setCreatedInInterestGroup(long createdInInterestGroup) {
		_createdInInterestGroup = createdInInterestGroup;
	}

	private String _semanticsName;
	private String _semanticsURI;
	private long _createdInInterestGroup;
}