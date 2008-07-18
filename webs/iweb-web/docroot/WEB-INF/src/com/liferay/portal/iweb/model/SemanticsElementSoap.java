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

package com.liferay.portal.iweb.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

/**
 * <a href="SemanticsElementSoap.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 *
 */
public class SemanticsElementSoap implements Serializable {
	public static SemanticsElementSoap toSoapModel(SemanticsElement model) {
		SemanticsElementSoap soapModel = new SemanticsElementSoap();

		soapModel.setElementURI(model.getElementURI());
		soapModel.setSemanticsURI(model.getSemanticsURI());

		return soapModel;
	}

	public static SemanticsElementSoap[] toSoapModels(
		List<SemanticsElement> models) {
		List<SemanticsElementSoap> soapModels = new ArrayList<SemanticsElementSoap>(models.size());

		for (SemanticsElement model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SemanticsElementSoap[soapModels.size()]);
	}

	public SemanticsElementSoap() {
	}

	public String getPrimaryKey() {
		return _elementURI;
	}

	public void setPrimaryKey(String pk) {
		setElementURI(pk);
	}

	public String getElementURI() {
		return _elementURI;
	}

	public void setElementURI(String elementURI) {
		_elementURI = elementURI;
	}

	public String getSemanticsURI() {
		return _semanticsURI;
	}

	public void setSemanticsURI(String semanticsURI) {
		_semanticsURI = semanticsURI;
	}

	private String _elementURI;
	private String _semanticsURI;
}